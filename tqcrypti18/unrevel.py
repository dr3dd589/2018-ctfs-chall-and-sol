import random
import string
seed_value = 42
random.seed(seed_value)

def random_string():
  size = 20
  chars = string.ascii_uppercase + string.ascii_lowercase + string.digits + "#_"
  return ''.join(random.choice(chars) for _ in range(size))

for i in range(seed_value-1):
    random_string()

print(random_string())