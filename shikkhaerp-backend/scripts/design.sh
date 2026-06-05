#!/bin/bash
# Save as show-structure.sh and run: chmod +x show-structure.sh && ./show-structure.sh

echo "📁 ShikkhaERP Backend Structure"
echo "================================"
echo ""

find . -type d -not -path "./.git/*" -not -path "./target/*" -not -path "./.idea/*" | sort | while read dir; do
    depth=$(echo "$dir" | tr -cd '/' | wc -c)
    indent=""
    for ((i=1; i<depth; i++)); do
        indent="$indent│   "
    done
    if [ $depth -gt 0 ]; then
        echo "$indent├── $(basename "$dir")/"
    else
        echo "$(basename "$dir")/"
    fi
done

find . -type f \( -name "*.java" -o -name "*.yml" -o -name "*.properties" -o -name "*.sql" -o -name "*.sh" -o -name "Dockerfile" -o -name "pom.xml" -o -name "*.html" \) -not -path "./.git/*" -not -path "./target/*" -not -path "./.idea/*" | sort | while read file; do
    depth=$(echo "$file" | tr -cd '/' | wc -c)
    indent=""
    for ((i=1; i<depth; i++)); do
        indent="$indent│   "
    done
    echo "$indent├── $(basename "$file")"
done
this is ok
pls giveme file name