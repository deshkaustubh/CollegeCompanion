package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.letterGenerator


data class LetterTemplate(
    val id: String,
    val emoji: String,
    val title: String,
    val tags: List<String>,
    val description: String,
    val requiredFields: List<String>,
    val generateLetter: (Map<String, String>) -> String
)

val letterTemplates = listOf(

    // 1. 📅 Leave Letter
    LetterTemplate(
        id = "leave_letter",
        emoji = "📅",
        title = "General Leave Letter",
        tags = listOf("leave", "absence", "sick", "personal"),
        description = "Formal request for short-term personal or medical leave from college.",
        requiredFields = listOf("recipient", "studentName", "fromDate", "toDate", "reason"),
        generateLetter = { fields ->
            """
                ${fields["recipient"]}
                
                Subject: Request for Leave from ${fields["fromDate"]} to ${fields["toDate"]}

                Respected Sir/Madam,

                I, ${fields["studentName"]}, request leave for the period ${fields["fromDate"]} to ${fields["toDate"]} due to ${fields["reason"]}. I kindly request you to consider my application and grant me leave for the mentioned period.

                I assure you that I will make up for any missed work during my absence.

                Thanking you in anticipation.

                Yours sincerely,  
                ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 2. 🏛 Bonafide Certificate
    LetterTemplate(
        id = "bonafide_request",
        emoji = "🏛",
        title = "Bonafide Certificate Request",
        tags = listOf("bonafide", "internship", "passport", "official"),
        description = "Request for a Bonafide Certificate for official documentation.",
        requiredFields = listOf("recipient", "studentName", "purpose"),
        generateLetter = { fields ->
            """
                ${fields["recipient"]}
                
                Subject: Request for Bonafide Certificate

                Respected Sir/Madam,

                I, ${fields["studentName"]}, am a bonafide student of your esteemed institution. I kindly request you to issue a Bonafide Certificate for the purpose of ${fields["purpose"]}. 

                I shall be grateful if the certificate is issued at the earliest convenience.

                Thanking you,

                Yours faithfully,  
                ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 3. 📄 Letter of Recommendation
    LetterTemplate(
        id = "lor_request",
        emoji = "📄",
        title = "Letter of Recommendation (LOR)",
        tags = listOf("lor", "recommendation", "internship", "pg", "job"),
        description = "Request a professor to provide a letter of recommendation.",
        requiredFields = listOf("recipient", "studentName", "purpose", "relation"),
        generateLetter = { fields ->
            """
                ${fields["recipient"]}

                Subject: Request for Letter of Recommendation

                Respected Sir/Madam,

                I hope this message finds you well. I am writing to humbly request a Letter of Recommendation in support of my application for ${fields["purpose"]}. During my time at the college, I had the privilege to work under your guidance in the capacity of ${fields["relation"]}, which I believe would add significant credibility to my application.

                I would be happy to provide any further information or documents required for this purpose.

                I sincerely hope you consider this request favorably.

                Yours respectfully,  
                ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 4. 🏨 Hostel Leave
    LetterTemplate(
        id = "hostel_leave",
        emoji = "🏨",
        title = "Hostel Leave Letter",
        tags = listOf("hostel", "warden", "leave"),
        description = "Request to leave campus hostel for personal/medical reasons.",
        requiredFields = listOf(
            "recipient",
            "studentName",
            "fromDate",
            "toDate",
            "reason",
            "roomNumber"
        ),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Hostel Leave Application from ${fields["fromDate"]} to ${fields["toDate"]}

        Respected Sir/Madam,

        I, ${fields["studentName"]}, residing in Room No. ${fields["roomNumber"]}, request your permission to leave the hostel from ${fields["fromDate"]} to ${fields["toDate"]} due to ${fields["reason"]}. I assure you that I will return on time and follow all necessary hostel protocols.

        Kindly approve my request at your earliest convenience.

        Yours sincerely,  
        ${fields["studentName"]}
        """.trimIndent()
        }
    ),

    // 5. 💸 Fee Concession
    LetterTemplate(
        id = "fee_concession",
        emoji = "💸",
        title = "Fee Concession Request",
        tags = listOf("finance", "fees", "support"),
        description = "Request for fee waiver due to financial hardship.",
        requiredFields = listOf("recipient", "studentName", "semester", "reason"),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Request for Fee Concession for ${fields["semester"]} Semester

        Respected Sir/Madam,

        I am ${fields["studentName"]}, currently in the ${fields["semester"]} semester. I request a fee concession due to ${fields["reason"]}. My family is experiencing financial constraints, and this support would greatly aid my continued education.

        I hope my application will be considered sympathetically.

        Yours faithfully,  
        ${fields["studentName"]}
        """.trimIndent()
        }
    ),

    // 6. 🔁 Transfer Certificate
    LetterTemplate(
        id = "transfer_certificate",
        emoji = "🔁",
        title = "Transfer Certificate Request",
        tags = listOf("tc", "transfer", "migration"),
        description = "Apply for TC when leaving the institution.",
        requiredFields = listOf("recipient", "studentName", "reason", "lastDate"),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Request for Transfer Certificate

        Respected Sir/Madam,

        I, ${fields["studentName"]}, request a Transfer Certificate as I will no longer be continuing my studies at this institution due to ${fields["reason"]}. My last date of attendance will be ${fields["lastDate"]}.

        Kindly issue the certificate at your earliest convenience.

        Thank you.  
        ${fields["studentName"]}
        """.trimIndent()
        }
    ),

    // 7. 🧾 ID Card Reissue
    LetterTemplate(
        id = "id_reissue",
        emoji = "🧾",
        title = "ID Card Reissue Request",
        tags = listOf("id", "lost", "reissue"),
        description = "Formal request to replace a lost student ID.",
        requiredFields = listOf("recipient", "studentName", "idNumber", "reason"),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Application for Reissue of Lost ID Card

        Respected Sir/Madam,

        I, ${fields["studentName"]}, request a reissue of my student ID card (ID: ${fields["idNumber"]}) which was misplaced due to ${fields["reason"]}. I understand the importance of campus ID and request a replacement.

        Thank you.  
        ${fields["studentName"]}
        """.trimIndent()
        }
    ),

    // 8. 🧪 Lab Equipment Request
    LetterTemplate(
        id = "lab_equipment",
        emoji = "🧪",
        title = "Lab Equipment Request",
        tags = listOf("lab", "equipment", "borrow"),
        description = "Letter requesting temporary issue of lab equipment.",
        requiredFields = listOf("recipient", "studentName", "equipment", "purpose", "returnDate"),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Request to Issue Lab Equipment

        Respected Sir/Madam,

        I, ${fields["studentName"]}, request the issue of ${fields["equipment"]} for ${fields["purpose"]}. I assure you the equipment will be returned in working condition by ${fields["returnDate"]}.

        Thanking you for your support.  
        ${fields["studentName"]}
        """.trimIndent()
        }
    ),

    // 9. 💬 Meet Faculty Request
    LetterTemplate(
        id = "meet_faculty",
        emoji = "💬",
        title = "Request to Meet Faculty",
        tags = listOf("appointment", "professor", "meeting"),
        description = "Letter to request meeting with faculty.",
        requiredFields = listOf("recipient", "studentName", "reason", "preferredDate"),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Request for Appointment

        Respected Sir/Madam,

        I, ${fields["studentName"]}, would like to request an appointment to discuss ${fields["reason"]}. I am available on ${fields["preferredDate"]} as per your convenience.

        I hope you will grant me a brief meeting.

        Yours sincerely,
        ${fields["studentName"]}
        """.trimIndent()
        }
    ),

    // 10. 🤕 Medical Leave
    LetterTemplate(
        id = "medical_leave",
        emoji = "🤕",
        title = "Medical Leave Letter",
        tags = listOf("leave", "medical", "sick"),
        description = "Formal letter for leave due to health issues.",
        requiredFields = listOf("recipient", "studentName", "fromDate", "toDate", "illness"),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Medical Leave Application

        Respected Sir/Madam,

        I, ${fields["studentName"]}, am suffering from ${fields["illness"]} and request medical leave from ${fields["fromDate"]} to ${fields["toDate"]}. A medical certificate is attached for your reference.

        Kindly grant me leave for the mentioned period.

        Yours sincerely,
        ${fields["studentName"]}
        """.trimIndent()
        }
    ),

    // 11. 📚 Attendance Justification
    LetterTemplate(
        id = "attendance_shortage",
        emoji = "📚",
        title = "Short Attendance Justification",
        tags = listOf("attendance", "below 75", "justification"),
        description = "Letter explaining low attendance with reason.",
        requiredFields = listOf("recipient", "studentName", "reason"),
        generateLetter = { fields ->
            """
        ${fields["recipient"]}

        Subject: Justification for Attendance Shortage

        Respected Sir/Madam,

        I, ${fields["studentName"]}, would like to provide justification for my attendance shortage. The low percentage is due to ${fields["reason"]}, and I humbly request your kind consideration when reviewing my record.

        Thanking you in advance.
        ${fields["studentName"]}
        """.trimIndent()
        }
    )
)
