import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.MileStone
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import kotlin.math.min

@Preview
@Composable
fun ProgressBarPreview() {
    RateMyPropertyTheme() {
        val currentStep = 3
        MileStoneProgressBar(
            listOf("Address", "Features", "Picture", "Review"),
            currentStep = currentStep
        )
    }
}

/** MileStoneProgressBar
 * @param mileStones list of titles containing the title for each milestone. ONLY 5 allowed at max
 * @param currentStep indicates the currently active step. Changing this value will recompose
 * the widget, every step before the current step is marked
 * complete and steps after this are marked pending
 *
 * **NOTE:** The range for current step is (1 to mileStones.length)
 * **/
@Composable
fun MileStoneProgressBar(mileStones: List<String>, currentStep: Int) {
    val maxSteps = 5
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        //Generation code is to ensure there is a progress line connecting two checkboxes

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            for (i in 0 until min(mileStones.size, maxSteps)) {
                val state = let {
                    if (i < currentStep) {
                        MileStoneState.Complete
                    } else if (i == currentStep) {
                        MileStoneState.Current
                    } else {
                        MileStoneState.Pending
                    }
                }
                MileStone(
                    modifier = Modifier.weight(1f),
                    state = state,
                    stepName = mileStones[i],
                    isFirst = i==0,
                    isLast = i == min(mileStones.size, maxSteps)-1
                )
            }

        }

    }
}

enum class MileStoneState {
    Complete,
    Current,
    Pending
}


@Composable
fun MileStone(
    modifier: Modifier = Modifier,
    state: MileStoneState,
    stepName: String,
    isLast: Boolean,
    isFirst: Boolean
) {
    val robotBolt = FontFamily(
        Font(R.font.robotobold)
    )
    val stepColor = Color(red = 19, green = 188, blue = 255, alpha = 255)

    Column(
        modifier = modifier
            .wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            val canvasSize = DpSize(width = 20.dp, height = 20.dp)

            Canvas(
                modifier = Modifier
                    .height(canvasSize.height)
                    .fillMaxWidth()
            ) {
                val nextLink = Path().let {
                    it.moveTo(this.size.width / 2, this.size.height / 2)
                    it.lineTo(this.size.width, this.size.height / 2)
                    it.close()
                    it
                }

                val prevLink = Path().let {
                    it.moveTo(this.size.width / 2, this.size.height / 2)
                    it.lineTo(0f, this.size.height / 2)
                    it.close()
                    it
                }

                if (!isLast) {
                    drawPath(
                        nextLink,
                        color = stepColor,
                        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                    )

                }
                if (!isFirst) {
                    drawPath(
                        prevLink,
                        color = stepColor,
                        style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
            }

            when (state) {
                MileStoneState.Pending -> {
                    Canvas(
                        modifier = Modifier
                            .size(canvasSize)
                            .align(Alignment.Center)
                            .border(
                                shape = CircleShape,
                                width = 2.dp,
                                color = stepColor
                            )
                    ) {
                        drawCircle(Color.White)
                    }
                }

                MileStoneState.Complete -> {
                    Box(modifier = Modifier.align(Alignment.Center)) {
                        Canvas(
                            modifier = Modifier
                                .size(canvasSize)
                        ) {

                            drawCircle(color = stepColor)
                            val tickMark = Path().let {
                                it.moveTo(this.size.width * 0.15f, this.size.height * 0.45f)
                                it.lineTo(this.size.width * 0.35f, this.size.height * 0.75f)
                                it.moveTo(this.size.width * 0.35f, this.size.height * 0.75f)
                                it.lineTo(this.size.width * 0.85f, this.size.height * 0.30f)
                                it.moveTo(this.size.width * 0.85f, this.size.height * 0.30f)
                                it.lineTo(this.size.width * 0.85f, this.size.height * 0.30f)
                                it.close()
                                it
                            }

                            drawPath(
                                path = tickMark,
                                color = Color.White,
                                style = Stroke(
                                    width = (this.size.width * 0.15f),
                                    cap = StrokeCap.Round
                                )
                            )

                        }
                    }
                }

                MileStoneState.Current -> {
                    Canvas(
                        modifier = Modifier
                            .size(canvasSize)
                            .align(Alignment.Center)
                            .border(
                                shape = CircleShape,
                                width = 2.dp,
                                color = stepColor
                            )
                    ) {
                        drawCircle(Color.White)
                        drawCircle(stepColor, radius = 10f)
                    }
                }
            }
        }
        Text(
            text = stepName,
            fontFamily = robotBolt,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }

}
