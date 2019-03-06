#!/usr/bin/python2 -u
from pwn import * 
flag = ""

for j in range(1,33):
    p = remote('18.218.238.95', 12345)
    p.recvuntil('Enter your text here:')
    my_msg = "A"*32+"B"*(32-j)
    p.sendline(my_msg)
    rcv = p.recv_raw(1024).split('\n')
    enc_text = rcv[1].decode('hex')
    p.close()

    for i in range(32,128):
       q = remote('18.218.238.95', 12345)
       q.recvuntil('Enter your text here:')
       msg = "A"*32+"B"*(32-j) + flag + chr(i)
       print(flag+chr(i))
       q.sendline(msg)
       rcv = q.recv_raw(1024).split('\n')
       y = rcv[1].decode('hex')
       q.close()
       if y[:64] == enc_text[:64]:
           flag += chr(i)
           print(flag)
           break
print(flag)