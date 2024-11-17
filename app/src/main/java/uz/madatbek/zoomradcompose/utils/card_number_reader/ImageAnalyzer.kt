//package uz.madatbek.zoomradcompose.utils.card_number_reader
//
//import androidx.camera.core.ExperimentalGetImage
//import androidx.camera.core.ImageAnalysis
//import androidx.camera.core.ImageProxy
//import androidx.core.text.isDigitsOnly
//import com.google.mlkit.vision.common.InputImage
//import com.google.mlkit.vision.text.TextRecognition
//import com.google.mlkit.vision.text.latin.TextRecognizerOptions
//
//@ExperimentalGetImage
//class ImageAnalyzer(
//    private val textListener: (CardScanData) -> Unit,
//) :
//    ImageAnalysis.Analyzer {
//    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
//
//    override fun analyze(imageProxy: ImageProxy) {
//        val mediaImage = imageProxy.image
//        if (mediaImage != null) {
//            val image = InputImage.fromMediaImage(
//                mediaImage, imageProxy.imageInfo.rotationDegrees
//            )
//
//            recognizer.process(image).addOnSuccessListener { result ->
//                var cardNumber = ""
//                var cardDate = ""
//
//                for (block in result.textBlocks) {
//                    for (line in block.lines) {
//                        val lineText = line.text
//
//                        if (
//                            lineText.length == 19 && lineText[4] == lineText[9] && lineText[9] == lineText[14] && lineText[14] == ' '
//                            && lineText.substring(0, 4).isDigitsOnly()
//                            && lineText.substring(5, 9).isDigitsOnly()
//                            && lineText.substring(10, 14).isDigitsOnly()
//                            && lineText.substring(15, 19).isDigitsOnly()
//                        ) {
//                            cardNumber = lineText
//                        }
//
//                        if (lineText.length == 5 && lineText[2] == '/' &&
//                            lineText.substring(0, 2).isDigitsOnly() &&
//                            lineText.substring(3, 5).isDigitsOnly()
//                        ) {
//                            cardDate = lineText
//                        }
//                    }
//                }
//
//                if (cardNumber != "" && cardDate != "") {
//                    textListener(CardScanData(cardNumber, cardDate))
//                }
//
//
//                imageProxy.close()
//            }.addOnFailureListener { e ->
//                e.printStackTrace()
//            }
//        }
//    }
//}
//
//data class CardScanData(
//    val number: String,
//    val date: String,
//)