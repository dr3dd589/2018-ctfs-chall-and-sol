bytes = ['0xfe','0xa0','0x41','0xf1','0xcc','0x9d','0x64','0xe5']
flag = 'picoCTF{'
l = []

for i in range(0,8):
    l.append(ord(flag[i]) ^ int(bytes[i], 16))

s = '0x'
for e in l[::-1]:
    sn = str(hex(e)).replace('0x', '')
    if len(sn) < 2:
        s += '0'
    s += sn

print(s)
print(str(int(s,16)))