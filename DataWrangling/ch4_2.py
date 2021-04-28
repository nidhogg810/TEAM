#快速逐元素数组函数
#%%
import numpy as np

arr = np.arange(10)
print(arr)
print(np.sqrt(arr))
print(np.exp(arr))

arr2 = np.random.randn(7)*5
print("arr2:")
print(arr2)
#modf,内建函数divmod的向量版本。返回两个向量，分别时浮点值数组的小数部分和整数部分
remainder, whole_part = np.modf(arr2)
print("remainder:")
print(remainder)
print("whole_part:")
print(whole_part)
# %%                                                                                                        
