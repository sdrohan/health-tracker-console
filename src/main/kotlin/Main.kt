import models.User
import utils.isValidEmail
import utils.isValidInRange
import utils.readValidEmail
import utils.validGender

val user = User()

/**
 * Starts the app and calls the perpetual menu
 *
 * @author Siobhan Drohan
 */
fun main(){
    println("Welcome to Health Tracker")
    runApp()
}

/**
 * Perpetually calls the main menu and executes the relevant code block based on user selection
 */
fun runApp(){
    var input: Int
    do {
        input = menu()
        when(input) {
            1 -> addUser()
            2 -> listUser()
            in(3..6) -> println("Feature coming soon")
            0 -> println("Bye...")
            else -> print("Invalid Option")
        }
    } while (input != 0)
}

/**
 * Displays the menu and returns the [user selection]
 */
fun menu(): Int{
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List User
        |  0. Exit
        |Please enter your option: """.trimMargin())
    return readLine()?.toIntOrNull() ?: -1
}

/**
 * Requests details from console and adds the user
 */
fun addUser(){
    println("Please enter the following for the user:")
    print("    Name: ")
    user.name = readLine()!!

    //Validation approach: user is asked repeatedly to enter the correct email format
    user.email = readValidEmail()

    print("    Id: ")
    user.id = readLine()?.toIntOrNull() ?: -1

    //Validation approach: user is asked once to enter a value in a range.  Default is provided if outside of range.
    print ("   Height (meters): ")
    val height = readLine()?.toFloatOrNull() ?: 0f
    if (isValidInRange(0.5,3.0, height.toDouble()))
        user.height = height
    else
        user.height = 0f

    //Validation approach: user is asked once to enter a value in a range.  Default is provided if outside of range.
    print ("   Weight (kg): ")
    val weight = readLine()?.toDoubleOrNull() ?: .0
    if (isValidInRange(25.0,500.0, weight))
        user.weight = weight
    else
        user.weight = 0.0

    //Validation approach: user is asked once to enter the gender, if they don't
    // enter a valid value, a ' '  is defaulted.
    print ("   Gender (M/F/O): ")
    user.gender = validGender(readLine()!!.getOrNull(0) ?: ' ')
}

/**
 * Displays the user to the console
 */
fun listUser(){
    println("The user details are: $user")
}

