import numpy as np
#列表生成ndarray
data1 = [6, 7.5, 8, 0, 1]
arr1 = np.array(data1)
print(arr1)
#生成全0的ndarray
print(np.zeros(10))
#生成全1的ndarray
print(np.ones(10))
#生成一个未初始化数值的ndarray
print(np.empty(10))

#相应的like方法
print(np.zeros_like((2,3)))
#生成一个随机的6元素ndarray
y = np.arange(6)
#调整y为2*3的列表
y = y.reshape((2,3))
y = np.zeros_like(y)
print(y)
print(type((2,3)))
