sealed class NfcTagResult {
    data class NewTag(val uid: String) : NfcTagResult()
    data class ExistingTag(val uid: String, val alias: String) : NfcTagResult()
}