import os
import subprocess
import sys
from PIL import Image, ImageDraw, ImageFont

# Path definitions
BASE_DIR = "/Users/baskaran/.gemini/antigravity/scratch/Cognifyz-Software-Development-Internship"
JAVA_BIN = "/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin/java"

print("Starting asset generation script...")

# 1. Run each Java app with simulated input and record terminal outputs
outputs = {}

# Task 1 Output
t1_proc = subprocess.run([JAVA_BIN, "-cp", f"{BASE_DIR}/Level-1/Task-1-Text-Based-Game/src", "Main"],
                         input="50\n75\n62\n68\n", text=True, capture_output=True)
outputs["t1"] = t1_proc.stdout

# Task 2 Output
t2_proc = subprocess.run([JAVA_BIN, "-cp", f"{BASE_DIR}/Level-1/Task-2-Number-Patterns/src", "Main"],
                         input="3\n5\n4\n5\n6\n", text=True, capture_output=True)
outputs["t2"] = t2_proc.stdout

# Task 3 Output
t3_proc = subprocess.run([JAVA_BIN, "-cp", f"{BASE_DIR}/Level-2/Task-3-CRUD-Task-Manager/src", "Main"],
                         input="2\n1\nComplete Task 4\nBuild temperature converter\nCompleted\n2\n5\n", text=True, capture_output=True)
outputs["t3"] = t3_proc.stdout

# Task 4 Output
t4_proc = subprocess.run([JAVA_BIN, "-cp", f"{BASE_DIR}/Level-2/Task-4-Temperature-Converter/src", "Main"],
                         input="1\n25\n2\n98.6\n4\n", text=True, capture_output=True)
outputs["t4"] = t4_proc.stdout

# Task 5 Output
t5_proc = subprocess.run([JAVA_BIN, "-cp", f"{BASE_DIR}/Level-3/Task-5-Persistent-Task-Manager/src", "Main"],
                         input="2\n1\nPush Code to GitHub\nPublish internship repository\nIn Progress\n2\n6\n", text=True, capture_output=True)
outputs["t5"] = t5_proc.stdout

# Task 6 Output
t6_proc = subprocess.run([JAVA_BIN, "-cp", f"{BASE_DIR}/Level-3/Task-6-Web-Scraper/src", "Main"],
                         input="1\n4\n", text=True, capture_output=True)
outputs["t6"] = t6_proc.stdout


# Function to generate dark-mode terminal output image
def render_terminal_screenshot(text, output_path, title="Terminal Output"):
    lines = [f"$ java Main"] + text.strip().split('\n')[:35] # max 35 lines
    font_size = 14
    padding = 20
    line_height = 20
    
    # Estimate width and height
    max_len = max(len(line) for line in lines) if lines else 40
    img_width = max(800, max_len * 9 + padding * 2)
    img_height = len(lines) * line_height + padding * 2 + 35

    img = Image.new("RGB", (img_width, img_height), color=(30, 30, 30))
    draw = ImageDraw.Draw(img)

    # Window title bar
    draw.rectangle([0, 0, img_width, 35], fill=(45, 45, 45))
    # Red, Yellow, Green window control dots
    draw.ellipse([15, 12, 25, 22], fill=(255, 95, 86))
    draw.ellipse([32, 12, 42, 22], fill=(255, 189, 46))
    draw.ellipse([49, 12, 59, 22], fill=(39, 201, 63))
    
    try:
        font = ImageFont.truetype("/System/Library/Fonts/Supplemental/Courier New.ttf", font_size)
    except:
        font = ImageFont.load_default()

    draw.text((80, 8), title, fill=(200, 200, 200), font=font)

    y = 35 + padding
    for line in lines:
        if line.startswith("$"):
            color = (80, 250, 123) # Green prompt
        elif "SUCCESS" in line or "CONGRATULATIONS" in line:
            color = (80, 250, 123)
        elif "ERROR" in line or "Invalid" in line:
            color = (255, 85, 85)
        elif "=" in line or "-" in line:
            color = (139, 233, 253) # Cyan headers
        else:
            color = (248, 248, 242) # Bright text
        draw.text((padding, y), line, fill=color, font=font)
        y += line_height

    os.makedirs(os.path.dirname(output_path), exist_ok=True)
    img.save(output_path)
    print(f"Generated screenshot: {output_path}")

# Render PNG screenshots
render_terminal_screenshot(outputs["t1"], f"{BASE_DIR}/Level-1/Task-1-Text-Based-Game/screenshots/output.png", "Task 1 - Number Guessing Game Output")
render_terminal_screenshot(outputs["t2"], f"{BASE_DIR}/Level-1/Task-2-Number-Patterns/screenshots/output.png", "Task 2 - Pattern Generator Output")
render_terminal_screenshot(outputs["t3"], f"{BASE_DIR}/Level-2/Task-3-CRUD-Task-Manager/screenshots/output.png", "Task 3 - Console Task CRUD Output")
render_terminal_screenshot(outputs["t4"], f"{BASE_DIR}/Level-2/Task-4-Temperature-Converter/screenshots/output.png", "Task 4 - Temperature Converter Output")
render_terminal_screenshot(outputs["t5"], f"{BASE_DIR}/Level-3/Task-5-Persistent-Task-Manager/screenshots/output.png", "Task 5 - Persistent Task Manager Output")
render_terminal_screenshot(outputs["t6"], f"{BASE_DIR}/Level-3/Task-6-Web-Scraper/screenshots/output.png", "Task 6 - Interactive Web Scraper Output")

print("All screenshots generated successfully.")
