<<<<<<< HEAD
# 4WAY_LRU
=======
## CSARCH2-Cache-Simulation-Project---Group2
 The **Cache Simulation Project** is designed to give an overview on the scenarios of the assigned cache mapping and replacement policy. This project contains various test case scenarios to analyze and differentiate each case.
 
 ## Specifications
 * Cache line = 16 words
 * Number of cache blocks = 32 blocks
 * Read Policy: non load-through
 * Full Associative + MRU
 
 ## Test cases
 1. Sequential sequence: up to 2n cache block. Repeat the sequence four times. Example: 0,1,2,3,...,63 {4x}
    ![sequential test](https://github.com/user-attachments/assets/4ec7b81e-8d78-4d0c-8688-446e21ced7d4)
 
 2. Random sequence: containing 4n main memory blocks.
    ![midrepeat](https://github.com/user-attachments/assets/b3dcaefc-306a-44da-b579-bc831f7a2047)
 
 3. Mid-repeat blocks: Start at block 0, repeat the sequence in the middle two times up to n-1 blocks, after
   which continue up to 2n. Then, repeat the sequence four times. Example: if n=8, sequence=0, 1,2,3,4,5,6,7
   1,2,3,4,5,6,7 8,9,10,11,12,13,14,15 {4x}
   ![random test](https://github.com/user-attachments/assets/1d42da82-5afa-4ae1-947f-9ed0d6947059)
>>>>>>> 9d9f21f3f7ad4ac4443639a9ec86b14aa9f4cb02
