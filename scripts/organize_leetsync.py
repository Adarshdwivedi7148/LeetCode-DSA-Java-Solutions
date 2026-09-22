import os
import re
import shutil
import subprocess
import urllib.request
import json

PATTERN_PRIORITY = [
    ("sliding-window", "04-Sliding-Window"),
    ("two-pointers", "03-Two-Pointers"),
    ("linked-list", "06-Linked-List"),
    ("binary-search", "07-Binary-Search"),
    ("monotonic-stack", "09-Monotonic-Stack"),
    ("stack", "08-Stack"),
    ("tree", "10-Trees"),
    ("heap", "11-Heap"),
    ("priority-queue", "11-Heap"),
    ("backtracking", "12-Backtracking"),
    ("greedy", "13-Greedy"),
    ("graph", "14-Graph"),
    ("dynamic-programming", "15-DP"),
    ("hash-table", "02-Hashing"),
    ("hashing", "02-Hashing"),
    ("array", "01-Arrays"),
]

def changed_root_dirs():
    try:
        output = subprocess.check_output(
            ["git", "diff-tree", "--no-commit-id", "--name-only", "-r", "HEAD^", "HEAD"],
            text=True
        )
    except Exception:
        output = ""
    roots = set()
    for path in output.splitlines():
        root = path.split("/", 1)[0]
        if re.match(r"^\d+-", root):
            # Ignore folders already using our numbered pattern prefixes.
            if not re.match(r"^(0[1-9]|1[0-5])-", root):
                roots.add(root)
    return sorted(roots)

def get_tags(slug):
    query = """
    query($titleSlug: String!) {
      question(titleSlug: $titleSlug) {
        topicTags { slug }
      }
    }
    """
    payload = json.dumps({
        "query": query,
        "variables": {"titleSlug": slug}
    }).encode()
    request = urllib.request.Request(
        "https://leetcode.com/graphql",
        data=payload,
        headers={"Content-Type": "application/json", "User-Agent": "Mozilla/5.0"},
        method="POST"
    )
    try:
        with urllib.request.urlopen(request, timeout=15) as response:
            data = json.loads(response.read().decode())
        question = data.get("data", {}).get("question") or {}
        return [x["slug"] for x in question.get("topicTags", [])]
    except Exception as exc:
        print("LeetCode tag lookup failed:", exc)
        return []

def choose_pattern(folder):
    match = re.match(r"^(\d+)-(.*)$", folder)
    if not match:
        return None
    slug = match.group(2)
    tags = get_tags(slug)
    print(folder, "tags:", tags)

    for tag, destination in PATTERN_PRIORITY:
        if tag in tags:
            return destination

    # Safe fallback for common names if the LeetCode API is unavailable.
    text = slug.lower()
    if "duplicate-ii" in text or "sliding-window" in text:
        return "04-Sliding-Window"
    if "linked-list" in text or "add-two-numbers" in text or "palindrome-linked-list" in text:
        return "06-Linked-List"
    if any(x in text for x in ["anagram", "ransom", "isomorphic", "jewels", "intersection", "two-sum", "duplicate", "unique-character"]):
        return "02-Hashing"
    if "binary-search" in text:
        return "07-Binary-Search"
    return "01-Arrays"

def main():
    for folder in changed_root_dirs():
        destination_parent = choose_pattern(folder)
        if not destination_parent:
            continue

        source = folder
        destination = os.path.join(destination_parent, folder)
        os.makedirs(destination_parent, exist_ok=True)

        if os.path.isdir(destination):
            for name in os.listdir(source):
                src = os.path.join(source, name)
                dst = os.path.join(destination, name)
                if os.path.isdir(src):
                    if os.path.exists(dst):
                        shutil.rmtree(dst)
                    shutil.copytree(src, dst)
                    shutil.rmtree(src)
                else:
                    shutil.copy2(src, dst)
                    os.remove(src)
            shutil.rmtree(source)
        else:
            shutil.move(source, destination)

        print("Organized:", folder, "->", destination)

if __name__ == "__main__":
    main()
