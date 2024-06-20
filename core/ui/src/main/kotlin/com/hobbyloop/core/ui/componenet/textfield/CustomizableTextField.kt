package com.hobbyloop.core.ui.componenet.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.HobbyLoopColor

@Composable
fun CustomizableTextField(
    modifier: Modifier,
    textFieldStyle: TextFieldStyle,
    value: String,
    isValid: Boolean,
    onValueChange: (String) -> Unit,
    hintMessage: String,
    errorMessage: String,
    paddingContent: PaddingValues = PaddingValues(horizontal = 15.dp, vertical = 16.dp),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    backgroundColor: Color = HobbyLoopColor.Gray20,
    textColor: Color = HobbyLoopColor.Gray80,
    placeholderColor: Color = HobbyLoopColor.Gray40,
    textSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight(500),
    textAlignment: Alignment = Alignment.CenterStart,
    errorTextAlignment: Alignment = Alignment.CenterEnd,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(8.dp),
) {
    val interactionSource = remember { MutableInteractionSource() }

    if (textFieldStyle == TextFieldStyle.PHONE_NUMBER_STYLE) {
        LaunchedEffect(key1 = isValid) {
            if (isValid){
                keyboardController?.hide()
            }
        }
    }

    BasicTextField(
        value = value,
        onValueChange = {
            val newValue = when(textFieldStyle) {
                TextFieldStyle.PHONE_NUMBER_STYLE -> {
                    it.filter { c -> c.isDigit() }.take(11)
                }
                TextFieldStyle.KOREAN_NAME_STYLE -> {
                    it.take(5)
                }
            }

            onValueChange(newValue)
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = roundedCornerShape
            ),
        singleLine = true,
        textStyle = TextStyle(
            color = textColor,
            fontSize = textSize,
            fontWeight = fontWeight
        ),
        cursorBrush = SolidColor(Color.Black),
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.padding(paddingContent)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hintMessage,
                        style = TextStyle(
                            color = placeholderColor,
                            fontSize = textSize,
                            fontWeight = fontWeight
                        ),
                        modifier = Modifier.align(textAlignment)
                    )
                }
                innerTextField()
                if (!isValid && value.isNotEmpty()) {
                    Row(
                        modifier = Modifier.align(errorTextAlignment),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Filled.Warning,
                            tint = Color.Red,
                            contentDescription = "오류 안내 아이콘"
                        )

                        Spacer(modifier = Modifier.width(2.dp))

                        Text(
                            text = errorMessage,
                            style = TextStyle(
                                color = Color.Red,
                                fontSize = 11.sp
                            ),
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomizableTextField() {
    var textValue by remember { mutableStateOf("") }

    CustomizableTextField(
        value = textValue,
        textFieldStyle = TextFieldStyle.KOREAN_NAME_STYLE,
        onValueChange = { newValue ->
            textValue = newValue
        },
        isValid = false,
        hintMessage = "이름을 입력하세요.",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        errorMessage = "현재 이름 형식이 옳지 않습니다.",
    )
}

enum class TextFieldStyle {
    KOREAN_NAME_STYLE,
    PHONE_NUMBER_STYLE
}
