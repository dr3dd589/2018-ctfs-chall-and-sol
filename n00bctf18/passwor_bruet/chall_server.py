
flag = "CTF{pwntools_use_kiya_na}"

def main():
	
	print "Welcome to the ADVANCED login portal, username=admin\n Enter Password with char by char!!"

	count = 1

	while True:
		print "Enter character number {}: ".format(count)
		char = raw_input()[0]
		if count == len(flag):
			break
		if char != flag[count-1]:
			print "Nah, you entered wrong character. I saved your time!!"
			break
		count+=1

if __name__=="__main__":
	main()
