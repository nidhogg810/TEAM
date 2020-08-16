#%%
import numpy as np 
import matplotlib.pyplot as plt  
import matplotlib.animation as ma 


T=np.linspace(0, 2*np.pi, 1024)

plt.axes(polar=True)
plt.plot(T,1. - np.sin(T),color='r')
plt.show()

# %%
