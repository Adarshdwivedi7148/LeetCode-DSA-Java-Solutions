# Automatic LeetCode -> Pattern-wise GitHub Sync

This repository uses LeetSync to receive accepted LeetCode submissions and a GitHub Action to organize each new submission into the DSA pattern structure.

## Flow

LeetCode
  -> Accepted submission
  -> LeetSync Chrome extension
  -> Temporary problem folder at repository root
  -> GitHub Action
  -> LeetCode topic tags
  -> DSA pattern folder

### Examples

- 1-two-sum -> 02-Hashing/
- 219-contains-duplicate-ii -> 04-Sliding-Window/
- 234-palindrome-linked-list -> 06-Linked-List/

Keep LeetSync connected to this repository. After you submit an accepted problem, LeetSync syncs it and the GitHub Action moves it into the pattern folder automatically.
