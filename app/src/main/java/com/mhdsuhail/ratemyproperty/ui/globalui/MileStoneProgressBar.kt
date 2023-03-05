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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import kotlin.math.min

@Preview
@Composable
fun ProgressBarPreview() {
    RateMyPropertyTheme() {
        MileStoneProgressBar(currentStep = 3, steps = 5)
    }
}


@Composable
fun MileStoneProgressBar(steps: Int, currentStep: Int) {
    val maxSteps = 5
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        //Generation code is to ensure there is a progress line connecting two checkboxes

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            for (i in 1..min(steps, maxSteps)) {
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
                    stepName = "Test $i"
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

            Divider(
                modifier = Modifier.align(Alignment.CenterStart),
                color = Color.LightGray,
                thickness = 2.dp
            )
            val canvasSize = DpSize(width = 20.dp, height = 20.dp)
            when (state) {
                MileStoneState.Pending -> {
                    Canvas(modifier = Modifier.size(canvasSize).align(Alignment.Center).border(
                        shape = CircleShape,
                        width = 2.dp,
                        color = stepColor
                    )){
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
