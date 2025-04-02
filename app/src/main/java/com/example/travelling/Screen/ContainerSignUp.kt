



import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelling.R
import com.example.travelling.State.SignUpScreenEvent
import com.example.travelling.State.SignUpState
import kotlinx.datetime.format.Padding

@Composable
fun TextFieldUpScreen(
    txt: String,
    label: String,
    onValueChange: (String) ->  Unit,

){
    TextField(value = txt, onValueChange = {
        onValueChange(it)
    },



        textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = {
            Text(text = label, color = Color(0xFF625b71))
        },

    )


}


@Composable
fun TextFieldUpPassword(
    text: String,
    label: String,
    onValueChange: (String) ->  Unit
){
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(value = text, onValueChange = {
        onValueChange(it)
    },

        label = {
            Text(text = label, color = Color(0xFF625b71))
        },

        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon  = {
            val image = if (passwordVisible)
                Icons.Filled.Check
            else Icons.Filled.Clear

            IconButton(onClick = {passwordVisible = !passwordVisible}){

            }
        }
    )
}
@Composable
fun ButtonUpScreen(
    text: String,
    onClick: () -> Unit
){
    Button(onClick = {
        onClick()
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6650a4)
        ),
        modifier = Modifier.width(150.dp)
    ) {
        Text(text = text)
    }
}
@Composable
fun ButtonUpLoading(
    text: String,
    onClick: () -> Unit
){
    Button(onClick = {
        onClick()
    },
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xff9b2d30)
        ),
        modifier = Modifier.width(150.dp)
    ) {
        Text(text = text)
    }
}