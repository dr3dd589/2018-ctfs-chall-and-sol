from z3 import *
import binascii, string, itertools
  
bits = 32
mask = 2**bits - 1
allowed_chars = string.printable
  
def convert_to_hex(s):
   return ''.join([hex(ord(x))[2:].zfill(2) for x in s[::-1]])
  
def convert_to_string(h):
   return ''.join([chr(int(x, 16)) for x in list(map(''.join, zip(*[iter(hex(h)[2:])]*2)))[::-1]])
  
def rot(val, steps):
   return (val << (bits-steps)) | LShR(val, steps)
  
def hash_foobar(input):
   eax = rot(input ^ 0x24f50094, (input ^ 0x94) & 0xf)
   eax = rot(eax + 0x2219ab34, bits - (eax + 0x34 & 0xf))
   eax = eax * 0x69a2c4fe
   return eax & mask
  
def break_iteratively(hashdata, i):
 
   if i == 0: prev_block = 0
   else: prev_block = hashdata[i-1]
 
   s = Solver()
   j = BitVec('current_block', bits)
  
   eax = rot(prev_block ^ hash_foobar(j), 7)
   s.add(eax == hashdata[i])
   block_preimages = []
  
   while s.check() == sat:
       sol = s.model()
       s.add(j != sol[j].as_long())
       block_string = convert_to_string(sol[j].as_long())
       if all(c in allowed_chars for c in block_string):
           block_preimages.append(block_string)
  
   return block_preimages
  
known = '9513aaa552e32e2cad6233c4f13a728a5c5b8fc879febfa9cb39d71cf48815e10ef77664050388a3' # this the hash of the file
data = list(map(''.join, zip(*[iter(known)]*8)))
hashdata = [int(x, 16) for x in data]
  
print '[+] Hash:', ''.join(data)
print '[+] Found potential hashes:\n'
for x in itertools.product(*[break_iteratively(hashdata, i) for i in range(10)]):
   print ' * ' + str(x)