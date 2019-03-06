from pwn import *
import gmpy2
import random




 

p = remote('39.96.8.114' ,9999)

p.recvuntil('Please input your number to guess the coeff: ')
p.sendline('1')

x = p.recv(1024)[17:]
print(x)

for i in range(100,120):
    if x == random.randint(0, gmpy2.next_prime(1 << i)):
        print(i)
        break

p.close()