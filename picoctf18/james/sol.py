# import urllib2
from pwn import *
import sys
import copy

BLOCK_SIZE = 16


def chunks(l, n):
    if n < 1:
        n = 1
    return [l[i:i + n] for i in range(0, len(l), n)]

# def httpcode(url):
# 	try:
# 		req = urllib2.Request(url)
# 		f = urllib2.urlopen(req)
# 		return f.getcode()
# 	except urllib2.HTTPError, e:
# 		return e.code

# def http(url):
# 	try:
# 		req = urllib2.Request(url)
# 		f = urllib2.urlopen(req)
# 		return f.read()
# 	except urllib2.HTTPError, e:
# 		return e.code

FLAG = ''
for m in range(1,3): # Since I choose "A"*16 as C[0], FLAG will be C[1],C[2]
    for n in range(BLOCK_SIZE-1,-1,-1):
        q = remote('2018shell1.picoctf.com', 15596)
        q.recvuntil('Send & verify (S)')
        q.sendline('E')
        q.recvuntil('Please enter your situation report: ')
        q.sendline("AAAAAAAAAAAAAAAA{0}".format('B'*(n%BLOCK_SIZE)))
        q.recvuntil('Anything else? ')
        q.sendline('AAAAAAAAAAAAAAAA{0}'.format('B'*(16-(n%BLOCK_SIZE))))
        x = q.recvuntil('Send & verify (S)').split('\n')[0][11:]
        q.close()
		# url = 'http://crypto-class.appspot.com/tetcon?p1=AAAAAAAAAAAAAAAA{0}&p2=AAAAAAAAAAAAAAAA{1}'.format('B'*(n%BLOCK_SIZE),'B'*(16-(n%BLOCK_SIZE)))
		
        cipher_block = chunks(x.decode('hex'),16)
		
        for i in range(256):
            print('.........................................................................',i,n,m)
            p = remote('2018shell1.picoctf.com', 15596)
            p.recvuntil('Send & verify (S)')
            p.sendline('S')
            p.recvuntil('Please input the encrypted message: ')
            xor_byte = ord(cipher_block[m][-1])
            new_block = copy.copy(cipher_block)
            new_block.append(new_block[m+1])
            new_block[len(cipher_block)-1] = '\xff'*15+chr(i)
            p.sendline(''.join(new_block).encode('hex'))
            y = p.recvuntil('Send & verify (S)')
			# url = 'http://crypto-class.appspot.com/tetcon?c={0}'.format(''.join(new_block).encode('hex'))
			# rescode = httpcode(url)
            if 'Successful' in y:
                c = 31 ^ i ^ xor_byte
                FLAG += chr(c)
                print '==>',chr(c),FLAG
                break

    print 'Flag:',FLAG