import java.io.{FileNotFoundException, FileWriter, IOException}
import scala.collection.IterableOnce.iterableOnceExtensionMethods
import scala.util.control.Breaks.break
import scala.util.matching.Regex

object PerlToScalaBasics extends App
{

  print("____Scala for Perlers____")

  print("""
  "Comments: Like C and here document with 3 double quotes

      Example:
      /* comment here */ - valid
      /*** ***/ - error
      /*
      */ - multi line is valid
  """)

  println("""Data Types to be defined in title case.
    Syntax: var/val keyword followed by variable name then colon and data type in title case
    Data Types:
    String
    Char
    Int
    Short - short int
    Long - Long int
    Float
    Double
    Byte
    Boolean - true / false - bare words

    A varialbe can also be defined without var / val but then it becomes global.
    Scala is strongly types but let the compiler infer the datatypes and later you can keep maintaining it""".stripMargin);

  val i: Int = 1;
  println("Int ", i);

  val c: Char = 'Z';
  println("Char ", c);

  val bool: Boolean = true;
  //  String st: "This is a scala string";
  //  print(st);

  val (a, b) = (1, 3); // group init and assignment like Perl
  println(a, b);

  // operators
  // +, -, / * % >> bit shift
  // everything is an object, hence operators can have alternate representation
  // e.g. 1 + 2 will translate to (1).+(2)
  println((1).+(2));
  // class methods are called with . operator unline -> in perl


  //Arrays:
  // to be created with Array constructor, accepts different data types like perl
  // access elements is with () instead of []
  val ar = Array(1, "v", 3, "ra", 'c');
  print(ar(2));
  // print(ar(20)); - this gives you array index out of bounds error unlike perl.

  // Array concatenation with ++ operator
  val ar1 = Array (1, 2, 3);
  val ar2 = Array (4, 5, 6);
  println(ar1 ++ ar2);

  // Perl's push :+=
  var ar3 = Array('X', 'Y', 'Z');
  ar3 :+= 'G';
  println(ar3.mkString(":")); // this is like printing whole array in double quotes like Perl

  // Perl's push +:=
  var ar4 = Array('R', 'Q', 'T' );
  ar4 +:= 'X';
  println(ar4.mkString(":")); // this is like printing whole array in double quotes like Perl

  // Define an array without initializing it with new keyword
  val emptyArray = new Array[Any](3);
  print(emptyArray.mkString("-"));


  val ar5 = new Array[Int](2);
  ar5(0) = 0;
  ar5(1) = 2;
  println("vvvv", ar5.mkString);

  // Define an array with fill keyword like in Pandas, with element as argument
  // https://stackoverflow.com/questions/3881013/array-initializing-in-scala
  val ar6 = Array.fill[Char](5)('X'); // c style calloc / malloc
  println(ar6.mkString(" filler "));

  val ar7 = Array.fill(4)(0); // without specifying data type
  println(ar7.mkString("x"));

  // multi dimensional arrays
  // val ar8 = Array.fill[Int](4,3)(0);
  val ar8 = Array.fill[Int](4)(1); // c style calloc / malloc
  println(ar8.mkString(" filler "));


  // range operator in Scala. like perl 1..10, it's 1 to 10
  println( (1 to 10).mkString(" X ") ); // joining with mkString

  // map and grep in per
  //grep
  val data = Array (1, 2, 3, 4, 5);
  println( data.filter {_ > 2}.mkString("x") ); // grep - join with 'x'

  //map _ is same as $_
  val nData = Array ("z", "b", "c");
  println(nData.map { x => x + "X"}.mkString("=>")); // x is like my $x, with functional sytax
  println(nData.map { _ + "X"}.mkString("=>")); // with perl type syntax
  println(nData.map ( _ + "X").mkString("=>")); // with round braces instead of curly braces

  // map and grep together
  val data2 = Array (1, 2, 3, 4, 5, 6, 7, 8, 9);
  println(
    data
      .filter {_ >= 2 } // grep
      .map { _ * 100 } // map
      .mkString(" Padding \n") // join
  );

  // reversing an array = .reverse
  println( (Array(1, 2, 3, 4)).reverse.mkString(" Pad ") );

  // shift - .take(x) - x number of elements to return.
  println(
    Array(1, 2, 3, 4, 5)
      .take(3)
      .mkString(":")
  );

  // pop - take right most element
  println(
    Array(1, 2, 3, 4)
      .takeRight(2)
      .mkString(":")
  );

  // unshift - add in the front- :+ < argument >
  val data3 = Array( 1, 2, 3, "f");
  println (
    (data3:+2).mkString("i")
  );
  println( (Array(1, 2, 3, "g", 4):+4).mkString(" > ") );
//  println( List(1, 2, 3)::List(11, 22, 33)::"XX"); Need to research more about :: operator
// Make a list element-by-element
// val when = "AM" :: "PM" :: Nil
// println(when);

  //push - add to the end :+ and ++

  // splice is .splice method
  println (
    (
      Array(
      1,2,3,4,5,6,7,8
    ).splitAt(4)
      )._1.mkString("X")
  );

  // if element exists in an array - perl uses any function to do that
  //https://stackoverflow.com/questions/2860226/how-can-i-check-if-a-perl-array-contains-a-particular-value
  println( Array(1, 2, 3).contains(1));

  // Hashes . Scala calls them maps

  var hash = Map(
    "a" -> "x",
    2 -> 4,
  )
  println ( hash.keys.mkString(">") ); // keys
  println ( hash.values.mkString(">") ); // values No each function line in perl.
  // each can be replicated with map

  // adding key paid to existing hash
  println(
    hash + ('x' -> 45)
  );

  // concatenate two hashes with ++
  var hash2 = Map(44 -> 55);
  println( hash2 ++ hash);

  // removing a key from hash map
  print(hash);
  val rt = hash - "a"; // the keys are not being deleted in place. Look into this. ???
  print(rt);

  // Scala allows you to assign default value to a hash access
  val hash3 = Map( "A" -> 1, "B" -> 2).withDefault( x => 12 );
  println(hash3("X")); // accessing value
  println(hash3.contains("A")); // exists

  // test
  var hsh = Map( "a" -> 1, "b" -> 2);
  hsh -= "a";
  println(hsh.mkString("\n"));

  // mutable maps - even though you are using val here, the hash is mutable
  // Like in perl, a fully qualified class name / object names can be used
  // and or an import can be made with import scala.collection.mutable.Map;
  // and then use the map
  val mutableHsh = scala.collection.mutable.Map(
    "A" -> 1,
    "B" -> 2
  );
  println(mutableHsh.mkString(" - "));
  mutableHsh -= "A";
  println(mutableHsh.mkString(" - "));


  // Like Perl Maps are used like Arrays here
  val aHsh = Map ( ("a", 1), ("B", 2));
  println(aHsh.mkString("-"));
  println((aHsh.keys));

  // zip function and toMap function to put all the elements together
  val bHsh = Map ( "A" -> 1, "B" -> 2, "C" -> 3);
  val ar10 = bHsh.keys;
  println(bHsh.keys.mkString(":"));
  val ar11 = bHsh.values;
  println(bHsh.values.mkString(":"));

  val cHsh = ar10.zip(ar11).toMap;
  println(bHsh.mkString(" <> "));
  println(cHsh.mkString(" <> "));


  // all the reading activity is in Console object.
  // You can read anything in it and read into anything like readLong, readChat, readByte
  print( Console.RED + "DANGER!" + Console.RESET + "\n");

  // conditionals: || && < > >= <= == != ()
  // if and else without curly braces is fine, e.g.

  if (1 > 0)
    println(1)
  else
    println(2)
  // same as
  if (1 < 0)
    {
      println(4);
    }
  else
    {
      println(5);
    }
  // no unless and no elseif. if you want do else if then use case statement

  // No ternary operators in Scala.
  // if always returns the last evaluated value e.g.

  val res = if (false) 2 else 3;
  println("Res value is " + res);

  //case statement in scala / Notice _ is used to make it match all as a wild card
  val aVar = 443;
  aVar match {
    case 2 => println("Not 2")
    case 3 => println("Not 3")
    case 44 => println("Not 44")
    case _ => println("match all")
  }

  // There are no string operators like eq, ne or even gt etc. Scala first checks the type
  // if the type matches then applies the operators accordingly

  // while Loop. There is no break. You can import break from utils.
//  while (true) {
//    println("Scala and Perl");
//    break;
//  }

  // do while
  do {
    println("Yo Perl");
  } while(false)

  //foreach loop
  val people = List(
    1,
    4
  )
  people.foreach(println);


  // forloop variable i in temp
  for (i <-  List( "this", "g", "g"  ) )
    {
      println(i);
    }

  //using util for range
  for (j <- 1 until 4) println(j);

  //iterating through collection, including string
  for (k <- "Perl")
    {
      println(k);
    }

  //splitting string
  for (str <- "this is good".split(" ")) println(str);

  // for loop with conditional
  val myLst = List( "A", "B", "C");
  for(str <- myLst if str == "A") println(str);

  // iterating through multi dimensional iterations, just separate them with semi colon
  for (i <- 1 to 2; j <- 2 to 4) println(i, j);

  //  foreach(), map(), flatMap(), filter() or withFilter().  ==??? look for these

  // Yield: Using yeild - Yield performs the operation after yield keyword
  val myLst2 = List ("dfa", "fdasfad", "fna23s");
  val uprLst = for ( i <- myLst2) yield i.capitalize;
  println(uprLst);

  // Alternate syntax to use Yield
  println(
    for {
      x <- 1 to 10
      y <- 0 to 3
    } yield (x, y)
  );


  //File IO:
  // All file ios are in io.Source class
  val fh = io.Source.fromFile(raw"D:\dev\large_dataset\simple_json.json"); // notice raw for literal string
  // https://docs.scala-lang.org/overviews/core/string-interpolation.html -- all printf formats
  fh.getLines().foreach(println); //fh.getLines() returns iterator which can be looped through
  //for ( x <- fh.getLines() ) println(x);
  //for ( str <- fh.getLines() ) println(str);

  // file reading in bash like format
  for {
      line <- io.Source.fromFile(raw"D:\dev\large_dataset\simple_json.json").getLines()
        } println(line);

  // file reading in a variable using mkString at he end
  val fullFileContents = io.Source.fromFile(raw"D:\dev\large_dataset\simple_json.json").getLines().mkString("");
  println(fullFileContents);

  // file reading in a variable with toList
  println(
    io.Source.fromFile(raw"D:\dev\large_dataset\simple_json.json")
      .getLines
      .toList
  );

  // io.Source - can read from any where, even http pages
  val dom = io.Source.fromURL(raw"http://404.com").getLines.mkString;
  println(dom);

  // Writing to a file:
  // There is nothing native to write to a file from Scala. Use Java classes

  val out = new FileWriter(raw"D:\dev\large_dataset\simple_json_out.json");
  out.write("This is not good. Do something");
  out.close();
  println(
    io.Source.fromFile(raw"D:\dev\large_dataset\simple_json_out.json")
      .getLines
      .mkString("")
  );

  // Executing external commands - perl system(), qx, ``, !
  // in scala import scala.sys.process._ everything.
  // ! - returns only exit code
  // !! - returs the output lines
  // .lines - returns the output lines

    import scala.sys.process._; // This is not working well, stays in executing mode and does not return
  // val retStatus = raw"C:\WINDOWS\system32\cmd.exe /v".!;
  // println(">>>" + retStatus);
  // println("XXXX", raw"C:\WINDOWS\system32\cmd.exe /v".!!);

  // .lines returns the output of the command.
      for {
        aLine <- raw"C:\WINDOWS\system32\cmd.exe /v".linesIterator
      } println(aLine);

  // system() equivalent is Process
  //  print(raw"C:\WINDOWS\system32\cmd.exe /v".run); - This has same problem like !, !!. the execution does not stop.

  //variations
  // Seq("ls", "-l") // seq is simpler version of List()
  // difference between list and seq - https://stackoverflow.com/questions/10866639/difference-between-a-seq-and-a-list-in-scala
  println(Seq("a", "b"));

  // @ARGV, @ARGC, Getopt::Long = scopt and scallop
  // @ARGV = argv() and $ARGV[0] = args(0)

  //Exceptions: try {} catch{} finally {}

  //Note: To throw an exception in Perl you'd use die() or croak().
  // In Scala, you use the throw. Unlike Perl, however, you can't just throw anything you want, be it an object, a string or even a function.
  // In Scala everything is an object, but throw expects you to throw a 'Throwable' object, which helps expose details
  // on whatever error you faced. The most common one is the 'Exception' object, which you can construct with a string describing your error:
  // Throwable objects contain some useful methods like toString(), getMessage() and printStackTrace()
  // that you can use to debug your program.

  // throw new Exception("I can't let you do that, Dave.")

  // Exception handling best practice:
  try {
    throw new Exception("I can't let you do that, Dave.")
    println("this line is never executed")
  } catch {
    case _: IOException => println("doSomething")
    case error: FileNotFoundException => println("doSomethingElse")
    case a: AssertionError => print("doYetAnotherThing")
    case unknown => println("Unknown error: " + unknown)
  }
  // read exception handling here: https://github.com/garu/scala-for-perl5-programmers


 // Subs: sub = def - functions are called WITHOUT PARENTHESIS
 def func { println("this func"); }
  // this function just prints something on the screen, it doesn't return anything.
  // This means the function is of the Unit type, and really cannot return anything - otherwise you'll trigger a compile-time error.
  // If you want to return something, you must specify the type during declaration and use the "=" sign before starting the block:

  def func2: Int = { return scala.util.Random.nextInt; }
  println( func2) ;

  // return keyword is optional - Scala returns last evaluated statement in the expression.
  // Single line functions
  def func3: Int = scala.util.Random.nextInt();
  println(func3);

  // and just like variables Scala can interprete return type of the functions too. So specifying Int is also not needed.
  def func4 = scala.util.Random.nextInt();
  println(func4);

  // Functions with arguments:

  def func4 (a: Int, b: Int) = a + b;
  println(func4(2, 4));

  // functions with default arugments
  def func5 (age: Int = 2) = 1;
  println( func5() );

  val datePattern = new Regex("""\d\d\d-\d\d-\d\d""");
  // which is same as
  val numPat = raw"\d+".r // .r makes a string regexp
  val nums = "1, 3, 4, 5, a";
  println("XXXX", numPat.findFirstIn(nums));
  for ( x <- numPat.findAllMatchIn(nums) ) println(x);
  // to match it use findFirstIn and findAllIn methods like this


  /** A 2-dimensional coordinate
   *
   * @constructor creates a new point with x and y values
   * @param x the point's coordinate in the x axis
   * @param y the point's coordinate in the y axis
   */
  class Point (initialX: Int, initialY: Int) {
    var x = initialX
    var y = initialY

    /** adds two points together
     *
     * @param p the Point object to be added
     * @return a new Point with the 2 points added
     */
    def +(p: Point): Point = new Point(x+p.x, y+p.y)
    def pr = println(x, y);
  }

  val p1 = new Point(3, 4);
  val p2 = new Point(23, 5);
  val p3 = p1 + p2;

  println(p3.pr);
}
