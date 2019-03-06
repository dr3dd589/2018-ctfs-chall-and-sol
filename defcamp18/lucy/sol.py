from pwn import *



for i in range(10000000,99999999):
    p = remote('167.99.143.206' ,65031)
    p.recvuntil('What is your name?')
    p.sendline("A")
    p.recvuntil('What number am I thinking of? [0/100]')
    p.sendline(str(i))
    x = p.recv(1024)
    if "correct" in x :
        print(i)
        break
    p.close()