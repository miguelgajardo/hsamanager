<h4>Problem Detection: </h4>

<p>An accountant company needs to share portable documents related with their client workers,
all of them exported from an accountability and payroll calculating system, that doesn't allow any other
viewer accounts, but paying a fee for a new account. </p>

<h4>Solution:</h4>

<p>
Develop a Portable Document Format Storage joining User roles, Client, Worker, Cost/Business Center and Periods.</p>

<h4>Warnings Before: It was created for covering the company needs, using
a Model View Controller pattern, with data access objects, servlet controllers, POJOs, and JSP views.</h4>
<p>It was developed using the most quantity of coding possible, in order to practice with data object controllers, SQL statements, Servlets with GET and POST requests and responses, and POJOs related with business model.</p>

<h3>This is <strong><u>not:</u></strong></h3>

<ul>
    <li>A sophisticated framework developed system.</li>
    <li>A persistence data application.</li>
    <li>A Rest API.</li>
    <li>A separated backend-frontend system.</li>
    <li>A Locale formatted implementation.</li>
</ul>
</p>


<h3>Functional Features:</h3>

<div>
<ul>
    <li>Administrator and User (viewer) roles.</li>
    <li>Administrator and User management interfaces.</li>
    <li>Companies (Principal Company's Clients) created by System Administrator(s).</li>
    <li>Create, List, Update and Delete companies (clients).</li>
    <li>Create, List, Update and Delete company's users.</li>
    <li>Create, List, Update and Delete company's workers.</li>
    <li>Create, List, Update and Delete company's business center (cost center).</li>
    <li>Upload, List, Update and Delete documents related to Worker or Business Center by period (MM/yyyy).</li>
    <li>Java Mail for contact use with JSP HTML forms.</li>
</ul>
</div>

<h3>UI Details</h3>
<div>
<ul>
    <li>Administrator and User dropdown menu.</li>
    <li>Corporative colors for visual comfort.</li>
    <li>GNU Licensed administration template with Bootstrap styles in rows and columns distributions.</li>
    <li>Datatables for Bootstrap 4 with JQuery (from datatables.net).</li>
</ul>
</div>

<h3>Developed to use with:</h3>
<ul>
    <li>Google App Engine deployment.</li>
    <li>Google Cloud Storage Buckets.</li>
    <li>Google CloudSQL Instance.</li>
</ul>


<h3>Relevant Configs:<h3>

<h5>At web xml:</h5>
<p>context-param; <br>
    param-name: miProyectoJava.bucket <br>
    param-value: ${bucket_id.bucket}
</p>

<h3>Screenshots</h3>
<h4>Principal Dashboard</h4>
<div>
    <img src="https://mgimgsgitrepository.s3-us-west-1.amazonaws.com/vista_principal_admin.png">
</div>
<h4>Created Worker View</h4>
<div>
    <img src="https://mgimgsgitrepository.s3-us-west-1.amazonaws.com/worker_create_view.png">
</div>
<h4>Uploaded PDF related to worker View</h4>
<div>
    <img src="https://mgimgsgitrepository.s3-us-west-1.amazonaws.com/uploaded_pdf_worker.png">
</div>
<h4>Principal Login View</h4>
<div>
    <img src="https://mgimgsgitrepository.s3-us-west-1.amazonaws.com/login_view.png">
</div>

