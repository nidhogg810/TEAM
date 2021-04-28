#柯里化：部分参数应用
#从一个函数衍生出新的函数
def add_orign(a, b):
    return a + b

#柯里化
add_five = lambda y: add_orign(5, y)
#使用pratial简化处理
from functools import partial
add_five2 = partial(add_orign, 5)

print(add_five(1))
print(add_five2(1))