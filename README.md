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
    ![seq](https://github.com/user-attachments/assets/7f410792-ed76-4b8d-bbb6-dc1f94604579)
    
    Sequential sequence runs in a consecutive manner. Therefore, the next accessed address is predicted. Additionally, the average access time decreased when the sequence is repeated because the accessed address is already found in the cache. It will then count as cache hit. Throughout the program, the total access time is consistently increasing even when the sequence is repeated. 
    
 3. Random sequence: containing 4n main memory blocks.
    ![random](https://github.com/user-attachments/assets/694ecef6-4dfa-40eb-972e-1301195662a6)
    
    Unlike sequential sequence, random sequence has randomized accessed address. The result of hit or miss varies on the address that is accessed therefore the it cannot be predicted and there's no specific pattern. 

 3. Mid-repeat blocks: Start at block 0, repeat the sequence in the middle two times up to n-1 blocks, after which continue up to 2n. Then, repeat the sequence four times. Example: if n=8, sequence=0, 1,2,3,4,5,6,7,1,2,3,4,5,6,7 8,9,10,11,12,13,14,15 {4x}
   ![mid](https://github.com/user-attachments/assets/3426d231-6e4b-4bf5-86b7-97fbb4fa33b6)

  Out of the three test cases, the mid-repeat blocks has the longest running time than that of the Random Sequence and Sequential Sequence. The total access time of mid-repeat blocks is consistently increasing throught the program. Since the user input 1024 as the number of memory blocks, the program will begin to have cache hit halfway. 

The test cases that were conducted were with a user input of 1024 MM Blocks. Results may slightly vary depending on the device the program was run on.

It was observed that the more the program goes through the sequence, the higher the hit rate becomes. The results also showed that the time depends on the sequence it is given, whether it is Sequential, Random, or Mid-Repeat, they run at different speeds, with Mid-Repeat being the slowest among them. It was also noted that at the first run, the Sequential sequence test case most of the time, if not always has a 100% miss rate, due to having a specific sequence of data and an empty cache at the first run, whereas in comparison to the Random sequence test case and Mid-Repeat sequence test case that already have a few hits in the cache.


