# Full Associative + MRU
## CSARCH2-Cache-Simulation-Project---Group2
 The **Cache Simulation Project** is designed to give an overview on the scenarios of the assigned cache mapping and replacement policy. This project contains various test case scenarios to analyze and differentiate each case.

You can access the program walkthrough here: https://drive.google.com/file/d/1_9QFjAlMUsYo9DPEUeWXGNi_NSsl2hDU/view?usp=sharing
 
 ## Specifications
 * Cache line = 16 words
 * Number of cache blocks = 32 blocks
 * Read Policy: non load-through
 * Number of memory blocks = minimum of 1024
 * Full Associative + MRU
 * Memory input: 1024

## How to run the program
 1. Download this program into ZIP file. 
 2. Extract the ZIP file and open the file with any IDE that is suitable for editing and runningg java applications.
 3. Run the CacheHome.java to run the program. This will display the pop-up window of the program.
    ![overview](https://github.com/user-attachments/assets/a197d4d1-641e-43a0-b569-5fe7953f7fd6)
 
 ## Test cases
 1. Sequential sequence: up to 2n cache block. Repeat the sequence four times. Example: 0,1,2,3,...,63 {4x}
    ![Screenshot 2025-03-22 171021](https://github.com/user-attachments/assets/e448d46c-9260-48cc-a0ee-bbb9a847f54f)
    
    Sequential sequence runs in a consecutive manner. Therefore, the next accessed address is predicted. Additionally, the average access time decreased when the sequence is repeated because the accessed address is already found in the cache. It will then count as cache hit. Throughout the program, the total access time is consistently increasing even when the sequence is repeated. 
    
 3. Random sequence: containing 4n main memory blocks.
    ![Screenshot 2025-03-22 171258](https://github.com/user-attachments/assets/5ed4cb04-d59c-4361-89f9-1614fae2fc03)
    
    Unlike sequential sequence, random sequence has randomized accessed address. The result of hit or miss varies on the address that is accessed therefore the it cannot be predicted and there's no specific pattern. 

 5. Mid-repeat blocks: Start at block 0, repeat the sequence in the middle two times up to n-1 blocks, after which continue up to 2n. Then, repeat the sequence four times. Example: if n=8, sequence=0, 1,2,3,4,5,6,7,1,2,3,4,5,6,7 8,9,10,11,12,13,14,15 {4x}
   ![Screenshot 2025-03-22 171537](https://github.com/user-attachments/assets/a518e55a-ce66-40c5-b5b1-343a6687e16e)

  Out of the three test cases, the mid-repeat blocks has the most longer running time than Random Sequence and Sequential Sequence. The total access time of mid-repeat blocks is consistently increasing throught the program. Since the user input 1024 as the number of memory blocks, the program will begin to have cache hit halfway. 


