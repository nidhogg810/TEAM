#%%
#numpy 数组算数
import numpy as np
#向量化计算
arr = np.array([[1,2,3],[2.1,3.5,7.7]])
print(arr * arr)
print(arr - arr)
#带标量的计算
print(1 / arr)
print(arr ** 0.5)

#同尺寸向量的比较
arr2 = np.array([[0., 4., 1.],[7., 2., 12.]])
print(arr2 > arr)
