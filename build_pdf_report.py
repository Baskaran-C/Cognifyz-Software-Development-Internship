import os
from reportlab.lib.pagesizes import letter
from reportlab.lib import colors
from reportlab.lib.units import inch
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle, Image, HRFlowable, PageBreak, KeepTogether
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.enums import TA_CENTER, TA_LEFT, TA_RIGHT, TA_JUSTIFY

pdf_filename = "/Users/baskaran/.gemini/antigravity/scratch/Cognifyz-Software-Development-Internship/Cognifyz_Software_Development_Internship_Guide.pdf"
base_dir = "/Users/baskaran/.gemini/antigravity/scratch/Cognifyz-Software-Development-Internship"

doc = SimpleDocTemplate(
    pdf_filename,
    pagesize=letter,
    rightMargin=40, leftMargin=40, topMargin=40, bottomMargin=40
)

styles = getSampleStyleSheet()

# Custom color palette
NAVY = colors.HexColor("#1A365D")
BLUE = colors.HexColor("#2B6CB0")
TEAL = colors.HexColor("#319795")
DARK_GRAY = colors.HexColor("#2D3748")
LIGHT_BG = colors.HexColor("#F7FAFC")
BORDER_COLOR = colors.HexColor("#E2E8F0")

title_style = ParagraphStyle(
    'CoverTitle',
    parent=styles['Normal'],
    fontName='Helvetica-Bold',
    fontSize=24,
    leading=28,
    textColor=NAVY,
    alignment=TA_CENTER,
    spaceAfter=12
)

subtitle_style = ParagraphStyle(
    'CoverSubtitle',
    parent=styles['Normal'],
    fontName='Helvetica',
    fontSize=13,
    leading=16,
    textColor=BLUE,
    alignment=TA_CENTER,
    spaceAfter=25
)

h1_style = ParagraphStyle(
    'H1',
    parent=styles['Normal'],
    fontName='Helvetica-Bold',
    fontSize=16,
    leading=20,
    textColor=NAVY,
    spaceBefore=15,
    spaceAfter=8
)

h2_style = ParagraphStyle(
    'H2',
    parent=styles['Normal'],
    fontName='Helvetica-Bold',
    fontSize=12,
    leading=15,
    textColor=BLUE,
    spaceBefore=10,
    spaceAfter=5
)

body_style = ParagraphStyle(
    'Body',
    parent=styles['Normal'],
    fontName='Helvetica',
    fontSize=9.5,
    leading=13.5,
    textColor=DARK_GRAY,
    spaceAfter=6,
    alignment=TA_JUSTIFY
)

code_style = ParagraphStyle(
    'Code',
    parent=styles['Normal'],
    fontName='Courier',
    fontSize=8,
    leading=10.5,
    textColor=colors.HexColor("#D69E2E"),
    backColor=colors.HexColor("#1A202C"),
    borderPadding=6,
    spaceBefore=4,
    spaceAfter=6
)

story = []

# --- COVER HEADER ---
story.append(Paragraph("COGNIFYZ TECHNOLOGIES", ParagraphStyle('Company', fontName='Helvetica-Bold', fontSize=14, alignment=TA_CENTER, textColor=TEAL)))
story.append(Paragraph("Software Development Internship Guide & Solution Suite", title_style))
story.append(Paragraph("Complete Implementation Guide for Tasks 1 through 6 (Java Full-Stack Portfolio)", subtitle_style))
story.append(HRFlowable(width="100%", thickness=2, color=NAVY, spaceAfter=15))

# --- INTERNSHIP OVERVIEW ---
story.append(Paragraph("1. About the Internship & Requirements", h1_style))
overview_text = (
    "Cognifyz Technologies is an innovative tech company specializing in AI, Machine Learning, Data Analytics, and enterprise technology solutions. "
    "The Software Development Internship program consists of 6 structured tasks grouped into 3 distinct difficulty levels. Interns are required to complete "
    "at least 4 out of 6 tasks. However, to construct a high-impact GitHub portfolio demonstrating Java proficiency, completing all 6 tasks is strongly recommended."
)
story.append(Paragraph(overview_text, body_style))

# Task Breakdown Table
table_data = [
    [Paragraph("<b>Level</b>", body_style), Paragraph("<b>Task</b>", body_style), Paragraph("<b>Difficulty</b>", body_style), Paragraph("<b>Project Built</b>", body_style), Paragraph("<b>Key Concepts</b>", body_style)],
    [Paragraph("Level 1", body_style), Paragraph("Task 1", body_style), Paragraph("Beginner", body_style), Paragraph("Text-Based Game", body_style), Paragraph("if/else, while, Scanner, Random", body_style)],
    [Paragraph("Level 1", body_style), Paragraph("Task 2", body_style), Paragraph("Beginner", body_style), Paragraph("Number Patterns", body_style), Paragraph("Nested for loops, rows & cols", body_style)],
    [Paragraph("Level 2", body_style), Paragraph("Task 3", body_style), Paragraph("Intermediate", body_style), Paragraph("Console Task CRUD", body_style), Paragraph("OOP, ArrayList, CRUD ops", body_style)],
    [Paragraph("Level 2", body_style), Paragraph("Task 4", body_style), Paragraph("Intermediate", body_style), Paragraph("Temperature Converter", body_style), Paragraph("Arithmetic methods, printf", body_style)],
    [Paragraph("Level 3", body_style), Paragraph("Task 5", body_style), Paragraph("Advanced", body_style), Paragraph("Persistent Task Manager", body_style), Paragraph("File I/O, tasks.txt, Exception handling", body_style)],
    [Paragraph("Level 3", body_style), Paragraph("Task 6", body_style), Paragraph("Advanced", body_style), Paragraph("Interactive Web Scraper", body_style), Paragraph("HttpURLConnection, DOM regex", body_style)],
]
t = Table(table_data, colWidths=[55, 45, 65, 120, 220])
t.setStyle(TableStyle([
    ('BACKGROUND', (0,0), (-1,0), LIGHT_BG),
    ('GRID', (0,0), (-1,-1), 0.5, BORDER_COLOR),
    ('VALIGN', (0,0), (-1,-1), 'MIDDLE'),
    ('BOTTOMPADDING', (0,0), (-1,-1), 4),
    ('TOPPADDING', (0,0), (-1,-1), 4),
]))
story.append(t)
story.append(Spacer(1, 10))

# --- LEVEL 1 SECTION ---
story.append(Paragraph("2. Level 1: Beginner Tasks", h1_style))
story.append(Paragraph("<b>Task 1: Basic Text-Based Game (Number Guessing Game)</b>", h2_style))
t1_desc = (
    "<b>Objective:</b> Implement game logic using conditional statements (if, else if, else) and user input loops.<br/>"
    "<b>Implementation Details:</b> The computer generates a secret random integer between 1 and 100. The player inputs guesses until correct. "
    "The application provides directional feedback ('Too High!' / 'Too Low!'), counts total attempts, and awards a performance rating."
)
story.append(Paragraph(t1_desc, body_style))

t1_img_path = f"{base_dir}/Level-1/Task-1-Text-Based-Game/screenshots/output.png"
if os.path.exists(t1_img_path):
    story.append(Image(t1_img_path, width=7*inch, height=2.2*inch))
story.append(Spacer(1, 8))

story.append(Paragraph("<b>Task 2: Generate and Print Number Patterns</b>", h2_style))
t2_desc = (
    "<b>Objective:</b> Utilize nested loops to control the spatial structure of number and star patterns.<br/>"
    "<b>Implementation Details:</b> An interactive menu allows users to select from 5 distinct geometric patterns including Increasing Number Triangle, "
    "Decreasing Number Triangle, Centered Star Pyramid, Palindromic Pyramid, and Floyd's Triangle."
)
story.append(Paragraph(t2_desc, body_style))

t2_img_path = f"{base_dir}/Level-1/Task-2-Number-Patterns/screenshots/output.png"
if os.path.exists(t2_img_path):
    story.append(Image(t2_img_path, width=7*inch, height=2.3*inch))
story.append(Spacer(1, 10))

# --- LEVEL 2 SECTION ---
story.append(PageBreak())
story.append(Paragraph("3. Level 2: Intermediate Tasks", h1_style))
story.append(Paragraph("<b>Task 3: Console CRUD Application (Task Manager)</b>", h2_style))
t3_desc = (
    "<b>Objective:</b> Implement Create, Read, Update, and Delete operations using object-oriented principles and dynamic lists.<br/>"
    "<b>Architecture:</b> Consists of <code>Task.java</code> (encapsulating id, title, description, status), <code>TaskManager.java</code> "
    "(encapsulating an <code>ArrayList&lt;Task&gt;</code> data store), and <code>Main.java</code> (CLI menu)."
)
story.append(Paragraph(t3_desc, body_style))

t3_img_path = f"{base_dir}/Level-2/Task-3-CRUD-Task-Manager/screenshots/output.png"
if os.path.exists(t3_img_path):
    story.append(Image(t3_img_path, width=7*inch, height=2.3*inch))
story.append(Spacer(1, 8))

story.append(Paragraph("<b>Task 4: Temperature Converter Program</b>", h2_style))
t4_desc = (
    "<b>Objective:</b> Convert temperatures between Celsius, Fahrenheit, and Kelvin scales.<br/>"
    "<b>Implementation Details:</b> Implements conversion formulas with double precision floating-point arithmetic, user direction selection, "
    "formatted tabular output via <code>System.out.printf</code>, and validation for physical limits (Absolute Zero / 0 Kelvin)."
)
story.append(Paragraph(t4_desc, body_style))

t4_img_path = f"{base_dir}/Level-2/Task-4-Temperature-Converter/screenshots/output.png"
if os.path.exists(t4_img_path):
    story.append(Image(t4_img_path, width=7*inch, height=2.2*inch))
story.append(Spacer(1, 10))

# --- LEVEL 3 SECTION ---
story.append(PageBreak())
story.append(Paragraph("4. Level 3: Advanced Tasks", h1_style))
story.append(Paragraph("<b>Task 5: Persistent CRUD Application Using File I/O</b>", h2_style))
t5_desc = (
    "<b>Objective:</b> Store task data persistently in a local text file (<code>tasks.txt</code>) to maintain state across application restarts.<br/>"
    "<b>Implementation Details:</b> Builds directly on Task 3 by introducing <code>TaskFileManager.java</code>. Utilizes <code>BufferedReader</code> "
    "and <code>BufferedWriter</code> with pipe-delimited text serialization. Includes robust exception handling for missing files and I/O errors."
)
story.append(Paragraph(t5_desc, body_style))

t5_img_path = f"{base_dir}/Level-3/Task-5-Persistent-Task-Manager/screenshots/output.png"
if os.path.exists(t5_img_path):
    story.append(Image(t5_img_path, width=7*inch, height=2.3*inch))
story.append(Spacer(1, 8))

story.append(Paragraph("<b>Task 6: Interactive Web Scraping Program</b>", h2_style))
t6_desc = (
    "<b>Objective:</b> Fetch live web content over HTTP and extract structured HTML elements for user presentation.<br/>"
    "<b>Implementation Details:</b> Uses <code>HttpURLConnection</code> to connect to web targets (Quotes to Scrape, Wikipedia, or custom URLs), "
    "extracting titles, section headers, quotes, authors, and hyperlinks using Regular Expressions (<code>Pattern</code> / <code>Matcher</code>)."
)
story.append(Paragraph(t6_desc, body_style))

t6_img_path = f"{base_dir}/Level-3/Task-6-Web-Scraper/screenshots/output.png"
if os.path.exists(t6_img_path):
    story.append(Image(t6_img_path, width=7*inch, height=2.3*inch))
story.append(Spacer(1, 10))

# --- SUBMISSION REQUIREMENTS & CHECKLIST ---
story.append(PageBreak())
story.append(Paragraph("5. Official Submission Requirements & LinkedIn Guidelines", h1_style))
submission_info = (
    "Cognifyz Technologies emphasizes academic integrity and professional presentation. Submitting copied code is strictly prohibited.<br/><br/>"
    "<b>LinkedIn Post & Video Guidelines:</b><br/>"
    "• Record a video (~4 mins) demonstrating all completed tasks and showing your GitHub repository.<br/>"
    "• Post the video on LinkedIn, tagging <b>@Cognifyz Technologies</b>.<br/>"
    "• Include official hashtags: <b>#cognifyz</b> <b>#cognifyzTech</b> <b>#cognifyzTechnologies</b>.<br/>"
    "• Save your project organized cleanly by levels in separate subdirectories."
)
story.append(Paragraph(submission_info, body_style))
story.append(Spacer(1, 10))

story.append(Paragraph("<b>Final Submission Checklist:</b>", h2_style))
checklist_data = [
    [Paragraph("<b>Status</b>", body_style), Paragraph("<b>Requirement</b>", body_style), Paragraph("<b>Verification</b>", body_style)],
    [Paragraph("[✓]", body_style), Paragraph("Task 1: Text-Based Number Guessing Game", body_style), Paragraph("Source, README, Screenshot", body_style)],
    [Paragraph("[✓]", body_style), Paragraph("Task 2: Number & Star Pattern Generator", body_style), Paragraph("Source, README, Screenshot", body_style)],
    [Paragraph("[✓]", body_style), Paragraph("Task 3: Console CRUD Task Manager", body_style), Paragraph("Source, README, Screenshot", body_style)],
    [Paragraph("[✓]", body_style), Paragraph("Task 4: Temperature Converter Program", body_style), Paragraph("Source, README, Screenshot", body_style)],
    [Paragraph("[✓]", body_style), Paragraph("Task 5: Persistent CRUD Task Manager (File I/O)", body_style), Paragraph("Source, tasks.txt, README, Screenshot", body_style)],
    [Paragraph("[✓]", body_style), Paragraph("Task 6: Interactive Web Scraper", body_style), Paragraph("Source, README, Screenshot", body_style)],
    [Paragraph("[✓]", body_style), Paragraph("Repository README & Documentation", body_style), Paragraph("Root README.md & PDF Guide", body_style)],
]
chk_table = Table(checklist_data, colWidths=[40, 300, 160])
chk_table.setStyle(TableStyle([
    ('BACKGROUND', (0,0), (-1,0), LIGHT_BG),
    ('GRID', (0,0), (-1,-1), 0.5, BORDER_COLOR),
    ('VALIGN', (0,0), (-1,-1), 'MIDDLE'),
    ('BOTTOMPADDING', (0,0), (-1,-1), 4),
    ('TOPPADDING', (0,0), (-1,-1), 4),
]))
story.append(chk_table)

doc.build(story)
print(f"PDF successfully built at: {pdf_filename}")
