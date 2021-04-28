#transposition swapaxes 转置与换轴
#%%
import numpy as np
arr = np.arange(15).reshape((3,5))
print(arr)
print("arr transposition:")
print(arr.T)
#计算矩阵内积a·b
print(np.dot(arr,arr.T))

#使用transpose，来转置轴
arr = np.arange(16).reshape((2,2,4))
print(arr)
print(arr.transpose((1,0,2)))#?x坐标和y坐标调转

#swapaxes方法
#接收一对轴编号作为参数，并对轴进行调整用于重组数据
#以下，由2*2*4变为2*4*2，交换y、z轴坐标
print(arr.swapaxes(1,2))
#达成与上文同样，交换x，y轴坐标的效果
print(arr.swapaxes(0,1))
#%%
