from flask import Flask, render_template, render_template_string, send_from_directory, request
from jinja2 import Environment, FileSystemLoader
from time import gmtime, strftime
template_dir = './templates'
env = Environment(loader=FileSystemLoader(template_dir))

app = Flask(__name__)
app.secret_key = open("flag.txt").read()

@app.route("/",methods=["GET"])
def home():
    return render_template("home.html")


@app.route("/result",methods=["POST"])
def output():
    answer = request.form['text']
    try:
        comment = render_template_string('''You Entered %s ''' % (answer))
    except:
        comment = "Error generating comment."
    return render_template("answer.html",showoff=comment)
    



if __name__ == "__main__":
    app.run(host='0.0.0.0', port=7777, threaded=True)