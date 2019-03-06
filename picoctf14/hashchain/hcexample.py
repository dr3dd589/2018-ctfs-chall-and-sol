import md5 #Must be run in python 2.7.x
from pwn import *

#code used to calculate successive hashes in a hashchain. 
seed = "65ded5353c5ee48d0b7d48c591b8f430"

#this will find the 5th hash in the hashchain. This would be the correct response if prompted with the 6th hash in the hashchain
hashc = seed
while(hashc != "4fe97e1399e74e64ac4853278929a7ab"):
  hashc = md5.new(hashc).hexdigest()
  print hashc


