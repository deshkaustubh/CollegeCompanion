package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.mailGenerator

data class MailTemplate(
    val id: String,
    val emoji: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val requiredFields: List<String>,
    val generateSubject: (Map<String, String>) -> String,
    val generateBody: (Map<String, String>) -> String
)

val mailTemplates = listOf(

    // 1. Bonafide Certificate Request
    MailTemplate(
        id = "bonafide",
        emoji = "🏛",
        title = "Bonafide Certificate Request",
        description = "Requesting certificate for internship/passport/etc.",
        tags = listOf("bonafide", "certificate"),
        requiredFields = listOf("studentName", "purpose"),
        generateSubject = { fields -> "Request for Bonafide Certificate - ${fields["purpose"]}" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, request a Bonafide Certificate for the purpose of ${fields["purpose"]}. Kindly issue the certificate at your earliest convenience.

            Thank you,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 2. Medical Leave Email
    MailTemplate(
        id = "medical_leave",
        emoji = "🤒",
        title = "Medical Leave Email",
        description = "Formal leave request due to illness.",
        tags = listOf("leave", "medical"),
        requiredFields = listOf("studentName", "fromDate", "toDate", "illness"),
        generateSubject = { fields -> "Medical Leave Request from ${fields["fromDate"]} to ${fields["toDate"]}" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, request medical leave from ${fields["fromDate"]} to ${fields["toDate"]} due to ${fields["illness"]}. I will submit a medical certificate as required.

            Kindly approve the leave.

            Regards,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 3. Hostel Leave Mail
    MailTemplate(
        id = "hostel_leave",
        emoji = "🏨",
        title = "Hostel Leave Email",
        description = "Request to leave hostel temporarily.",
        tags = listOf("hostel", "warden", "residence"),
        requiredFields = listOf("studentName", "fromDate", "toDate", "reason", "roomNumber"),
        generateSubject = { fields -> "Hostel Leave Application from ${fields["fromDate"]} to ${fields["toDate"]}" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, residing in Room No. ${fields["roomNumber"]}, request hostel leave from ${fields["fromDate"]} to ${fields["toDate"]} due to ${fields["reason"]}.

            Kindly grant permission.

            Thank you,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 4. Lab Equipment Request
    MailTemplate(
        id = "lab_equipment",
        emoji = "🧪",
        title = "Lab Equipment Request",
        description = "Request to borrow lab components or devices.",
        tags = listOf("lab", "equipment", "issue"),
        requiredFields = listOf("studentName", "equipment", "purpose", "returnDate"),
        generateSubject = { fields -> "Request to Issue ${fields["equipment"]} from Lab" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, request the issue of ${fields["equipment"]} for ${fields["purpose"]}. I assure timely return by ${fields["returnDate"]}.

            Kindly approve the request.

            Sincerely,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 5. Internship Permission Email
    MailTemplate(
        id = "internship_permission",
        emoji = "📩",
        title = "Internship Permission Mail",
        description = "Seek faculty/admin approval for an internship.",
        tags = listOf("internship", "permission"),
        requiredFields = listOf("studentName", "companyName", "duration"),
        generateSubject = { fields -> "Request for Internship Permission at ${fields["companyName"]}" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I am ${fields["studentName"]}, and I request approval for an internship at ${fields["companyName"]} for ${fields["duration"]}. The experience will align with my academic goals.

            Kindly confirm your approval.

            Regards,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 6. Attendance Justification
    MailTemplate(
        id = "attendance",
        emoji = "📚",
        title = "Attendance Shortage Justification",
        description = "Explain shortage below minimum %.",
        tags = listOf("attendance", "shortage", "reason"),
        requiredFields = listOf("studentName", "reason"),
        generateSubject = { fields -> "Request for Consideration of Attendance Shortage" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, wish to explain the attendance shortage in my academic record. Due to ${fields["reason"]}, I was unable to attend certain sessions. I kindly request your understanding and consideration.

            Thank you,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 7. Fee Concession Mail
    MailTemplate(
        id = "fee_concession",
        emoji = "💸",
        title = "Fee Concession Request Mail",
        description = "Formal request for financial relief.",
        tags = listOf("fees", "waiver", "support"),
        requiredFields = listOf("studentName", "semester", "reason"),
        generateSubject = { fields -> "Request for Fee Concession – ${fields["semester"]} Semester" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, am currently in the ${fields["semester"]} semester and request a fee concession due to ${fields["reason"]}. Your support will greatly help in continuing my education.

            Kindly consider this request.

            Sincerely,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 8. Request to Meet Faculty
    MailTemplate(
        id = "faculty_meeting",
        emoji = "💬",
        title = "Faculty Meeting Request",
        description = "Schedule a one-on-one with professor/admin.",
        tags = listOf("faculty", "doubt", "meeting"),
        requiredFields = listOf("studentName", "reason", "preferredDate"),
        generateSubject = { fields -> "Request for Meeting on ${fields["preferredDate"]}" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, would like to meet regarding ${fields["reason"]}. I am available on ${fields["preferredDate"]}, or as per your convenience.

            Kindly confirm availability.

            Regards,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 9. Transfer Certificate Request
    MailTemplate(
        id = "transfer_certificate",
        emoji = "🔁",
        title = "Transfer Certificate Request Mail",
        description = "Request to issue TC due to change in institution.",
        tags = listOf("tc", "certificate", "transfer"),
        requiredFields = listOf("studentName", "reason", "lastDate"),
        generateSubject = { fields -> "Request for Transfer Certificate" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, request a Transfer Certificate due to ${fields["reason"]}. My last working date will be ${fields["lastDate"]}. Kindly process the certificate at the earliest.

            Thanking you,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    ),

    // 10. Grievance Email
    MailTemplate(
        id = "grievance",
        emoji = "📝",
        title = "Grievance Submission Mail",
        description = "Communicate an issue respectfully.",
        tags = listOf("issue", "grievance", "complaint"),
        requiredFields = listOf("studentName", "issue"),
        generateSubject = { fields -> "Submission of Grievance Regarding ${fields["issue"]}" },
        generateBody = { fields ->
            """
            Respected Sir/Madam,

            I, ${fields["studentName"]}, wish to bring to your attention a concern regarding ${fields["issue"]}. I hope for a fair and prompt resolution. I am available for further discussion if needed.

            Thank you,  
            ${fields["studentName"]}
            """.trimIndent()
        }
    )
)
