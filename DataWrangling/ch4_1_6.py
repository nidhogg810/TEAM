#magic index
#%%
import numpy as np
#生成一个8*4的列表，第一层8个元素，第二层4个元素
arr = np.empty((8,4))
#print(arr)
for i in range(8):
    arr[i] = i
print("arr1:\n")
print(arr)
#中间[1,1]代表第一层元素的索引
print("arr1:\n")
print(arr[[1,1]])
#也可使用负索引
print("arr1:\n")
print(arr[[-2,-3]])

arr2 = np.arange(32).reshape((8,4))
print("arr2:\n")
print(arr2)
arr2_1= arr2[[1,5,7,2]]
print("arr2_1:\n")
print(arr2_1)
#以下代表获取（1,0）（5,3）（7,1） (2,2) 4个坐标点
arr2_2=arr2[[1,5,7,2],[0,3,1,2]]
print("arr2_2:\n")
print(arr2_2)
# %%
