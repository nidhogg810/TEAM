import numpy as np

a = np.array((1,2,3), dtype=np.float64)
print(a)
#dtype参数表示保存的数据的数据类型
a = np.zeros_like(a,dtype=np.int32)
print(a)
#astype显示转换dtype类型
arr = np.array([3.7, 5.2, 6.4, 8.1])
#float转换成int小数点后直接舍去
print(arr.astype(np.int32))
#如果元素里均是表达数字含义的字符串也可以强制转换
str_arr = np.array(['3.7', '5.2', '6.4', '8.1'])
print(str_arr)
print(str_arr.astype(np.float))
#或者反过来，可以转换撑bytes对象
print(arr.astype(np.string_))

#也可以使用另一个数组的dtype
int_array = np.arange(10)
caliables = np.array([.22,.270],dtype=float)
int_array = int_array.astype(caliables.dtype)
print(int_array)