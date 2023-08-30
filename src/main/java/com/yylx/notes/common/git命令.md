## Git将A分支新的文件同步到B分支

    git checkout B
    git checkout A file.txt

## git将A分支文件file的变更同步到B分支

    git checkout B
    git checkout --patch -A file.txt

## 统计项目中每个人的代码行数

git log --since ==2022-01.01 --until=2022-07-15 --format='%aN' | sort -u | while read name; do echo -en "$name\t"; git
log --author="$name" --pretty=tformat: --numstat | grep "\(.html\|.java\|.xml\|.properties\|.css\|.js\|.txt\)$" | awk '{
add += $1; subs += $2; loc += $1 - $2 } END { printf "added lines: %s, removed lines: %s, total lines: %s\n", add, subs,
loc }' -; done

