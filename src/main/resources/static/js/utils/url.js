const formatURL = () => {
    const source = window.location.href;
    const lastIndex = source.lastIndexOf("/");
    return source.slice(0,lastIndex+1);
}

export default {
    redirect(page) {
        window.location.replace(formatURL() + page + ".html");
    }
}