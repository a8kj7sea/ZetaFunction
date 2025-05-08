# ZetaFunction
A simple experiment to study the effectiveness of functions in the style of Zeta Lang, the language I am developing.


## What is a Zeta Function ?

The **Zeta Function** is part of an experimental design aimed at studying function structures before implementing the lexer and tokenizer for the **ZetaLang** programming language, which is currently in development.

### Key Concepts

* **Parameter Pre-Conditions**:
  Zeta functions support defining pre-conditions for parameters, such as validating numeric ranges or checking text values. This design is somewhat inspired by SQL-like constraint mechanisms.

* **Separated Syntax**:
  The function syntax separates the parameters from the body, which results in a slightly more verbose structure, but it is more readable and beginner-friendly.

* **Kotlin-Inspired Design**:
  The syntax and style of ZetaLang functions are influenced by Kotlin, aiming to balance clarity and conciseness.

<br>

```rust
fn zobe:
{
  print "zobreeee!";
}

fn sayHello:
[
  ~param(String) as name;
  ~param(Int) as age = 10 if age > 0;
]
{
  print "hi" , name;
}

```
